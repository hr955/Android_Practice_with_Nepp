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

    // 내가 투표한 진영의 id가 뭔지
    var mySideId = 0 // Int가 들어올 예정

    // 내가 투표한 진영 자체를 저장
    // 아직 투표를 하지 않았다면 -> null
    var mySelectedSide: SideData? = null

    companion object {
        // json {  } 를 넣으면 -> 파싱해서 -> TopicData 객체로 리턴해주는 함수
        fun getTopicDataFromJson(json: JSONObject): TopicData {
            // 결과로 사용될 TopicData 객체 생성
            val topicData = TopicData()

            // json 내부에서 값을 파싱 -> TopicData의 변수들에 대입
            topicData.id = json.getInt("id")
            topicData.title = json.getString("title")
            topicData.imageUrl = json.getString("img_url")

            // 내가 선택한 진영의 id
            topicData.mySideId = json.getInt("my_side_id")
            // 그 진영이 어떤건지? -> null로 내려오면 파싱 X
            if(!json.isNull("my_side")){
                // null이 아닐때만 파싱
                topicData.mySelectedSide = SideData.getSideDataFromJson(json.getJSONObject("my_side"))
            }

            // 토론 하위 정보로 -> sides 라는 JSONArray를 내려줌
            // JSONArray : for문 돌려서 파싱 -> topicData의 sideList에 추가해주기
            val sidesArr = json.getJSONArray("sides")

            for (i in 0 until sidesArr.length()) {
                val sideObj = sidesArr.getJSONObject(i)

                // JSONObject -> SideData() 로 변환
                val sideData = SideData.getSideDataFromJson(sideObj)

                // topicData의 sideList에 추가
                topicData.sideList.add(sideData)
            }

            // 최종 결과 산정
            return topicData
        }
    }

    // 보조 생성자 추가
    constructor() : this(0, "제목없음", "")

    // id 값만 받는 보조 생성자
    constructor(id: Int) : this(id, "제목없음", "")

}