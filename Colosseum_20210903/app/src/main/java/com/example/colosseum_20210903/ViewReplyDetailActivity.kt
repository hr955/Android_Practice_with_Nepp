package com.example.colosseum_20210903

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.colosseum_20210903.adatpers.ChildReplyAdapter
import com.example.colosseum_20210903.datas.ReplyData
import com.example.colosseum_20210903.utils.GlobalData
import com.example.colosseum_20210903.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_reply_detail.*
import org.json.JSONObject

class ViewReplyDetailActivity : BaseActivity() {

    lateinit var mReplyData: ReplyData
    var mChildReplyList = ArrayList<ReplyData>()
    lateinit var mChildReplyAdapter: ChildReplyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_reply_detail)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        // 답글 삭제 -> 리스트뷰 롱클릭 리스너
        childReplyListView.setOnItemLongClickListener { adapterView, view, position, l ->
            // 입력값 검증(validation -> 내가 작성한 답글이 아니라면 함수 강제종료
            val clickedReply = mChildReplyList[position]

            if(GlobalData.loginUser!!.id != clickedReply.writer.id){
                Toast.makeText(mContext, "자신이 적은 답글만 삭제할 수 있습니다", Toast.LENGTH_SHORT).show()
                return@setOnItemLongClickListener true
            }

            // 경고창
            val alert = AlertDialog.Builder(mContext)
            alert.setMessage("정말 해당 답글을 삭제하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->
                // 해당 답글 삭제 -> API 요청 + 새로고침

            })
            alert.setNegativeButton("취소", null)
            alert.show()

            return@setOnItemLongClickListener true
        }

        // 댓글 입력 버튼
        addReplyBtn.setOnClickListener {
            // 최소 5자 이상 작성 (입력값 검증)
            val inputContent = contentEdt.text.toString()

            if (inputContent.length < 5) {
                Toast.makeText(mContext, "최소 5자 이상 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            ServerUtil.postRequestReplyToParentReply(
                mContext,
                inputContent,
                mReplyData.id,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(jsonObj: JSONObject) {

                        getChildRepliesFromServer()

                        runOnUiThread {
                            contentEdt.setText("")

                            // 키보드 숨김처리
                            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                        }
                    }
                })
        }
    }

    override fun setValues() {
        mReplyData = intent.getSerializableExtra("replyData") as ReplyData
        sideAndNicknameTxt.text = "(${mReplyData.selectedSide.title}) ${mReplyData.writer.nickname}"
        replyContentTxt.text = mReplyData.content

        getChildRepliesFromServer()

        mChildReplyAdapter = ChildReplyAdapter(mContext, R.layout.child_reply_list_adapter, mChildReplyList)
        childReplyListView.adapter = mChildReplyAdapter

    }

    fun getChildRepliesFromServer(){
        ServerUtil.getRequestChildReplyList(mContext, mReplyData.id, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {
                super.onResponse(jsonObj)

                // 서버에서 댓글목록 불러와 List 채워주기
                val dataObj = jsonObj.getJSONObject("data")
                val replyObj = dataObj.getJSONObject("reply")
                val childReplyArr = replyObj.getJSONArray("replies")

                mChildReplyList.clear() // 중복출력 방지

                for (i in 0 until childReplyArr.length()) {
                    val childReplyObj = childReplyArr.getJSONObject(i)

                    mChildReplyList.add(ReplyData.getReplyDataFromJson(childReplyObj))
                }

                runOnUiThread {
                    mChildReplyAdapter.notifyDataSetChanged()

                    // 리스트뷰의 최하단(마지막 아이템)으로 이동
                    childReplyListView.smoothScrollToPosition(mChildReplyList.lastIndex)
                }
            }
        })
    }
}