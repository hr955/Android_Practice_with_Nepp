package com.example.colosseum_20210903

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.colosseum_20210903.adatpers.ReplyAdapter
import com.example.colosseum_20210903.datas.ReplyData
import com.example.colosseum_20210903.datas.TopicData
import com.example.colosseum_20210903.utils.GlobalData
import com.example.colosseum_20210903.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_topic_detail.*
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var mTopicData: TopicData

    val mReplyList = ArrayList<ReplyData>()
    lateinit var mReplyAdapter: ReplyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_topic_detail)

        setupEvents()
        setValues()
    }

    override fun onResume() {
        super.onResume()

        getTopicDetailDataFromServer()
    }

    override fun setupEvents() {

        // 댓글 삭제
        replyListView.setOnItemLongClickListener { adapterView, view, position, l ->
            val clickedReply = mReplyList[position]

            // 입력값 검증 -> 댓글 작성자와 사용자의 정보가 일치하는가?
            if(GlobalData.loginUser!!.id != clickedReply.writer.id){
                Toast.makeText(mContext, "자신이 작성한 댓글만 삭제할 수 있습니다", Toast.LENGTH_SHORT).show()

                return@setOnItemLongClickListener true
            }

            val alert = AlertDialog.Builder(mContext)
            alert.setMessage("정말 해당 댓글을 삭제하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->
                // 해당 답글 삭제 -> API 요청 + 새로고침
                ServerUtil.deleteRequestReply(mContext, clickedReply.id, object : ServerUtil.JsonResponseHandler{
                    override fun onResponse(jsonObj: JSONObject) {
                        runOnUiThread{
                            Toast.makeText(mContext, "답글이 삭제되었습니다", Toast.LENGTH_SHORT).show()
                        }
                        getTopicDetailDataFromServer()
                    }
                })
            })
            alert.setNegativeButton("취소", null)
            alert.show()

            return@setOnItemLongClickListener true
        }

        // 첫번째 진영, 두번째 진영 투표버튼의 이벤트
        // 두개의 버튼이 하는일이 거의 동일함 -> 코드를 한번만 짜서, 두개의 버튼에 똑같이 달아주자
        // 버튼이 눌리면 할 일(onClickListener)을 적어두는 변수 (Interface 변수)
        val ocl = object : View.OnClickListener {
            override fun onClick(view: View?) {
                // 버튼이 눌리면 할 일
                // view -> 눌린게 어떤 버튼인지? 눌린 버튼을 담아준다
                val clickedSideId = view!!.tag.toString().toInt()

                // 해당 진영에 투표하기 (서버에 투표 실행)
                ServerUtil.postRequestTopicVote(
                    mContext,
                    clickedSideId,
                    object : ServerUtil.JsonResponseHandler {
                        override fun onResponse(jsonObj: JSONObject) {
                            // 투표 결과 확인 -> 새로 투표 현황을 다시 받아오자
                            // 이전에 함수로 분리해둔, 서버에서 상세정보 받아오기 호출
                            getTopicDetailDataFromServer()
                        }
                    })
            }
        }

        voteToFirstSideBtn.setOnClickListener(ocl)
        voteToSecondSideBtn.setOnClickListener(ocl)

        // 댓글 등록 버튼
        addReplyBtn.setOnClickListener {
            // 투표를 해야만 댓글 작성 화면으로 이동가능
            // 선택한 진영이 없다면, myIntent 관련 코드 실행 X -> Validation (입력값 검증) 작업
            if(mTopicData.mySelectedSide == null){
                Toast.makeText(mContext, "투표를 진행해야 의견 등록이 가능합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // 결과 지정 : 함수를 강제 종료
            }else{
                val myIntent = Intent(mContext, EditReplyActivity::class.java)
                myIntent.putExtra("selectedSide", mTopicData.mySelectedSide)
                startActivity(myIntent)
            }
        }
    }

    override fun setValues() {
        mTopicData = intent.getSerializableExtra("TopicData") as TopicData

        // 투표 버튼에, 각 진영이 어떤 진영인지 "버튼에 메모" -> 투표할 때 그 진영이 뭔지 알아낼 수 있다
        voteToFirstSideBtn.tag = mTopicData.sideList[0].id
        voteToSecondSideBtn.tag = mTopicData.sideList[1].id

        Glide.with(mContext).load(mTopicData.imageUrl).into(topicImg)

        titleTxt.text = mTopicData.title

        // 나머지 데이터는 서버에서 가져오자 -> onResume에서 가져오는것으로 일원화
        // getTopicDetailDataFromServer()

        mReplyAdapter = ReplyAdapter(mContext, R.layout.reply_list_item, mReplyList)
        replyListView.adapter = mReplyAdapter
    }

    // 최신 토론 상세 데이터를 다시 서버에서 불러오기 -> 투표현황 등..
    fun getTopicDetailDataFromServer() {
        ServerUtil.getRequestTopicDetail(
            mContext,
            mTopicData.id,
            object : ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {
                    val dataObj = jsonObj.getJSONObject("data")
                    val topicObj = dataObj.getJSONObject("topic")

                    // mTopicData를 새로 파싱한 데이터로 교체
                    mTopicData = TopicData.getTopicDataFromJson(topicObj)

                    // 댓글을 새로 불러옴 -> 기존댓글 삭제 (중복 막기)
                    mReplyList.clear()

                    // topicObj 안에 댓글 목록도 같이 들어있다 -> 추가 파싱, UI 반영
                    val repliesArr = topicObj.getJSONArray("replies")

                    for (i in 0 until repliesArr.length()) {
                        // 댓글 {} json -> ReplyData 형태로 파싱 (변환) -> mReplyList 목록에 추가
//                        val replyObj = repliesArr.getJSONObject(i)
//                        val replyData = ReplyData.getReplyDataFromJson(replyObj)
//                        mReplyList.add(replyData)

                        mReplyList.add(ReplyData.getReplyDataFromJson(repliesArr.getJSONObject(i)))
                    }

                    // 새로 받은 데이터로 UI 반영 (득표 수 등등)
                    refreshTopicDataToUI()
                }
            })
    }

    // 새로 받은 데이터로 UI 반영 (득표 수 등등)
    fun refreshTopicDataToUI() {
        runOnUiThread {
            firstSideTitleTxt.text = mTopicData.sideList[0].title
            firstSideVoteCountTxt.text = "${mTopicData.sideList[0].voteCount}표"

            secondSideTitleTxt.text = mTopicData.sideList[1].title
            secondSideVoteCountTxt.text = "${mTopicData.sideList[1].voteCount}표"

            // 투표 여부에 따라 버튼에 다른 문구 적용
            if (mTopicData.mySideId == -1) {
                voteToFirstSideBtn.text = "투표하기"
                voteToSecondSideBtn.text = "투표하기"
            } else {
                if (mTopicData.mySideId == mTopicData.sideList[0].id) {
                    voteToFirstSideBtn.text = "취소하기"
                    voteToSecondSideBtn.text = "선택변경"
                } else {
                    voteToFirstSideBtn.text = "선택변경"
                    voteToSecondSideBtn.text = "취소하기"
                }
            }

            // 리스트뷰도 새로고침
            mReplyAdapter.notifyDataSetChanged()
        }
    }
}