package com.example.colosseum_20210903.datas

import org.json.JSONObject
import java.io.Serializable

class TopicData(
    var id: Int,
    var title: String,
    var imageUrl: String
) : Serializable {
    // 진영 목록을 담아줄 ArrayList
    val sideList = ArrayList<SideData>()

    companion object{
        // json {  } 를 넣으면 -> 파싱해서 -> TopicData 객체로 리턴해주는 함수
        fun getTopicDataFromJson(json : JSONObject) : TopicData {
            // 결과로 사용될 TopicData 객체 생성
            val topicData = TopicData()

            // json 내부에서 값을 파싱 -> TopicData의 변수들에 대입
            topicData.id = json.getInt("id")
            topicData.title = json.getString("title")
            topicData.imageUrl = json.getString("img_url")

            //최종 결과 산정
            return topicData
        }
    }

    // 보조 생성자 추가
    constructor() : this(0, "제목없음", "")

    // id 값만 받는 보조 생성자
    constructor(id: Int) : this(id, "제목없음", "")

}