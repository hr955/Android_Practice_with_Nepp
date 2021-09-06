package com.example.colosseum_20210903

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.colosseum_20210903.utils.ContextUtil

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {
    }

    override fun setValues() {

        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({
            // 1. 자동 로그인 여부 판단 -> 상황에 따라 다른 화면으로 넘어가도록
            // Intent의 목적지만 달라진다

            val myIntent: Intent

            // 자동 로그인 여부 : 사용자가 자동로그인 하겠다 + 저장된 토큰이 유효(들어있다)하다
            if (ContextUtil.getAutoLogin(mContext) && ContextUtil.getToken(mContext) != "") {
                // 둘다 만족 -> 자동로그인 -> 메인화면으로 이동
                myIntent = Intent(mContext, MainActivity::class.java)
            } else {
                myIntent = Intent(mContext, SignInActivity::class.java)
            }

            startActivity(myIntent)
            finish()
        }, 2500)
    }
}