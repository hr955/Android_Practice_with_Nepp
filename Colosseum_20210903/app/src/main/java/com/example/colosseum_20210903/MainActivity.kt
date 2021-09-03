package com.example.colosseum_20210903

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.colosseum_20210903.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        signInBtn.setOnClickListener {
            //입력한 아이디와 비번을 변수로 저장 -> 서버에서 일치여부 판단
            val inputId = emailEdt.text.toString()
            val inputPw = passwordEdt.text.toString()

            ServerUtil.postRequestSignIn(inputId, inputPw)

        }
    }

    override fun setValues() {
    }
}