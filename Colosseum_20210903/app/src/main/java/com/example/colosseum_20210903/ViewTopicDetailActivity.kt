package com.example.colosseum_20210903

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.colosseum_20210903.datas.TopicData
import kotlinx.android.synthetic.main.activity_view_topic_detail.*

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
    }
}