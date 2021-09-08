package com.example.colosseum_20210903.fcm

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFCMService : FirebaseMessagingService() {
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        // 푸시 알림을 받았을 때 실행시킬 코드
        Log.d("푸시알림", "수신 이벤트")

        val title = p0.notification!!.title

        // 핸들러 활용 -> UI 스레드 접근
        val myHandler = Handler(Looper.getMainLooper())
        myHandler.post {
            Toast.makeText(applicationContext, title, Toast.LENGTH_SHORT).show()
        }
    }
}