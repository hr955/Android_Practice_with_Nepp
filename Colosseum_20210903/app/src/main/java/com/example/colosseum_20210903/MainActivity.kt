package com.example.colosseum_20210903

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.colosseum_20210903.adatpers.TopicAdapter
import com.example.colosseum_20210903.datas.TopicData
import com.example.colosseum_20210903.datas.UserData
import com.example.colosseum_20210903.utils.ServerUtil
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.my_custom_action_bar.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mTopicList = ArrayList<TopicData>()
    lateinit var mTopicAdapter: TopicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {
        topicListView.setOnItemClickListener { adapterView, view, position, l ->
            val myIntent = Intent(mContext, ViewTopicDetailActivity::class.java)
            myIntent.putExtra("TopicData", mTopicList[position])
            startActivity(myIntent)
        }

        notiBtn.setOnClickListener {
            Toast.makeText(mContext, "알림 보러가기", Toast.LENGTH_SHORT).show()
        }
    }

    override fun setValues() {

        getMainDataFromServer()

        mTopicAdapter = TopicAdapter(mContext, R.layout.topic_list_item, mTopicList)
        topicListView.adapter = mTopicAdapter

        // backBtn 숨김처리
        backBtn.visibility = View.GONE
        notiBtn.visibility = View.VISIBLE
    }

    // 서버에서, 메인화면에 보여줄 정보 받아오기
    fun getMainDataFromServer() {
        ServerUtil.getRequestMainData(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(jsonObj: JSONObject) {
                // 응답 - jsonObj 분석(파싱) -> 토론 주제들을 서버가 내려줌
                val dataObj = jsonObj.getJSONObject("data")
                val topicsArr = dataObj.getJSONArray("topics")
                val userObj = dataObj.getJSONObject("user")

                //val userNickname = userObj.getString("nick_name")
                val loginUser = UserData.getUserDataFromJson(userObj)

                // 서버가 내려주는 토론주제들 (JsonObject 목록) -> TopicData로 변환해서 ArrayList에 추가
                for (i in 0 until topicsArr.length()) {
                    val topicObj = topicsArr.getJSONObject(i)
//                    val tempTopicData = TopicData()
//                    tempTopicData.id = topicObj.getInt("id")
//                    tempTopicData.title = topicObj.getString("title")
//                    tempTopicData.imageUrl = topicObj.getString("img_url")

                    val tempTopicData = TopicData.getTopicDataFromJson(topicObj)

                    // mTopicList에 하나씩 추가
                    mTopicList.add(tempTopicData)
                }

                // 목록의 변화 -> 리스트뷰가 인지 -> 새로고침 공지 -> 리스트뷰 변경 -> 백그라운드에서 UI 변경
                runOnUiThread {
                    mTopicAdapter.notifyDataSetChanged()
                    Toast.makeText(mContext, "${loginUser.nickname}님 환영합니다!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}