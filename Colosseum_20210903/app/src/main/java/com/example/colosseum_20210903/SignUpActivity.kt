package com.example.colosseum_20210903

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.colosseum_20210903.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        signUpBtn.setOnClickListener {
            // 1. 입력한 값을 받아서
            val inputEmail = emailEdt.text.toString()
            val inputPw = pwEdt.text.toString()
            val inputNickname = nicknameEdt.text.toString()

            // 2. 서버에 가입 요청 -> 응답에 따라 어떤 처리?
            ServerUtil.putRequestSingUp(inputEmail, inputPw, inputNickname, object: ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {
                    super.onResponse(jsonObj)
                }
            })
        }
    }

    override fun setValues() {
    }
}