package com.example.colosseum_20210903.datas

import org.json.JSONObject

class NotificationData(
    var id: Int,
    var title: String
) {

    constructor() : this(0, "")

    companion object {
         // JsonObject -> NotificationData 함수
        fun getNotificationFromJSON(json : JSONObject) : NotificationData {
            val notificationData = NotificationData()

            notificationData.id = json.getInt("id")
            notificationData.title = json.getString("title")

            return notificationData
        }
    }
}