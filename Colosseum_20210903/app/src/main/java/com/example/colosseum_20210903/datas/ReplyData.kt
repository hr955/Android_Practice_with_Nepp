package com.example.colosseum_20210903.datas

import org.json.JSONObject

class ReplyData(
    var id: Int,
    var content: String,
    var likeCount: Int,
    var hateCount: Int,
    var myLike: Boolean,
    var myHate: Boolean,
    var replyCount: Int
) {

    // ReplyData의 하위 개념들

    // 이 댓글이 지지하는 진영
    lateinit var selectedSide: SideData
    lateinit var writer: UserData

    constructor() : this(0, "", 0, 0, false, false, 0)

    companion object {
        // JSON을 넣으면 -> ReplyData로 변환해주는 기능
        fun getReplyDataFromJson(json: JSONObject): ReplyData {
            val replyData = ReplyData()

            replyData.id = json.getInt("id")
            replyData.content = json.getString("content")
            replyData.likeCount = json.getInt("like_count")
            replyData.hateCount = json.getInt("dislike_count")
            replyData.myLike = json.getBoolean("my_like")
            replyData.myHate = json.getBoolean("my_dislike")
            replyData.replyCount = json.getInt("reply_count")

            // 선택진영 파싱 -> SideData 의 파싱 기능 활용
            val selectedSideObj = json.getJSONObject("selected_side")
            replyData.selectedSide = SideData.getSideDataFromJson(selectedSideObj)

            // 댓글 작성자 정보 파싱 -> UserData 의 파싱 기능 활용
            val userObj = json.getJSONObject("user")
            replyData.writer = UserData.getUserDataFromJson(userObj)

            return replyData
        }
    }
}