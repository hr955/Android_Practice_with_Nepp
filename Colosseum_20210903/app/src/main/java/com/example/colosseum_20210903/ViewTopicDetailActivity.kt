package com.example.colosseum_20210903

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.colosseum_20210903.datas.ReplyData
import com.example.colosseum_20210903.datas.TopicData
import com.example.colosseum_20210903.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_topic_detail.*
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var mTopicData: TopicData

    val mReplyList = ArrayList<ReplyData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_topic_detail)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
    }

    override fun setValues() {
        mTopicData = intent.getSerializableExtra("TopicData") as TopicData
        Glide.with(mContext).load(mTopicData.imageUrl).into(topicImg)

        titleTxt.text = mTopicData.title

        // 나머지 데이터는 서버에서 가져오자
        getTopicDetailDataFromServer()
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
        }
    }
}