package com.example.colosseum_20210903

import android.os.Bundle
import com.example.colosseum_20210903.adatpers.NotificationAdapter
import com.example.colosseum_20210903.datas.NotificationData
import com.example.colosseum_20210903.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_notification.*
import org.json.JSONObject

class NotificationActivity : BaseActivity() {

    lateinit var mNotificationAdapter: NotificationAdapter
    val mNotificationList = ArrayList<NotificationData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
    }

    override fun setValues() {

        getNotificationListFromServer()

        mNotificationAdapter = NotificationAdapter(mContext, R.layout.notification_list_item, mNotificationList)
        notiListView.adapter = mNotificationAdapter

    }

    // 서버에서 알림 목록을 가져오는 함수
    fun getNotificationListFromServer() {
        ServerUtil.getRequestNotificationList(
            mContext,
            true,
            object : ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {
                    val dataObj = jsonObj.getJSONObject("data")
                    val notisArr = dataObj.getJSONArray("notifications")

                    for (i in 0 until notisArr.length()) {
                        val notiObj = notisArr.getJSONObject(i)
                        val notificationData = NotificationData.getNotificationFromJSON(notiObj)

                        mNotificationList.add(notificationData)
                    }

                    runOnUiThread {
                        mNotificationAdapter.notifyDataSetChanged()
                    }
                }
            })
    }
}