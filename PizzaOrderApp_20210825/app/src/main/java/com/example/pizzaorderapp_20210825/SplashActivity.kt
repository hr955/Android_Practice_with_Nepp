package com.example.pizzaorderapp_20210825

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.pizzaorderapp_20210825.base.BaseActivity

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
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(mContext, MainActivity::class.java))

        }, 3000)
    }
}