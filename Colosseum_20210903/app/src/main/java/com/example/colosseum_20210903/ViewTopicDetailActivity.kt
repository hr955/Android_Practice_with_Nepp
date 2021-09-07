package com.example.colosseum_20210903

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.colosseum_20210903.datas.TopicData
import com.example.colosseum_20210903.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_topic_detail.*
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var mTopicData: TopicData

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

                    // 새로 받은 데이터로 UI 반영 (득표 수 등등)

                }
            })
    }
}