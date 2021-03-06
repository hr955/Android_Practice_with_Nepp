package com.example.colosseum_20210903.datas

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class NotificationData(
    var id: Int,
    var title: String,
) {

    // 생성자와 관계없이 동작하는 멤버변수
    // val : Calendar 내부의 값만 변경, 변수 자체의 대입 X
    val createdAt = Calendar.getInstance() // default : 현재시간, 디바이스에 설정된 시간대(서울)

    constructor() : this(0, "")

    companion object {
        // JsonObject -> NotificationData 함수
        fun getNotificationFromJSON(json: JSONObject): NotificationData {
            val notificationData = NotificationData()

            notificationData.id = json.getInt("id")
            notificationData.title = json.getString("title")

            // 1. 서버가 알려주는 시간을 String으로 받기
            val createdAtStr = json.getString("created_at")

            // 2. 받아낸 String 을 -> Calendar에 대입 (SimpleDateFormat)
            val serverFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            notificationData.createdAt.time = serverFormat.parse(createdAtStr)

            // 3. (참고) 서버는 우리에게 UTC(GMT+0) -> 영국 시간대를 기준으로 시간을 알려줌
            // 본인의 시간대(TimeZone)를 알아내서 -> 시간값을 조정
            val localTimeZone = notificationData.createdAt.timeZone

            // 영국과의 시차 계산
            val timeDiff = localTimeZone.rawOffset / 1000 / 60 / 60

            // 알림 생성시간(영국) + 시차 -> 한국시간
            notificationData.createdAt.add(Calendar.HOUR, timeDiff)

            return notificationData
        }
    }
}