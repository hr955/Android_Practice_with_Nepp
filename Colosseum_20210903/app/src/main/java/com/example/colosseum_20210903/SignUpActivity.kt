package com.example.colosseum_20210903

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            ServerUtil.putRequestSingUp(
                inputEmail,
                inputPw,
                inputNickname,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(jsonObj: JSONObject) {
                        // code -> 가입성공(200), 실패(200 이외의 값)
                        // 실패사유 -> 이메일양식 X, 중복된 이메일, 중복된 닉네임
                        val code = jsonObj.getInt("code")

                        if (code == 200) {

                        } else {
                            // 실패시만 동작 -> message에 담긴 가입 실패 사유
                            val message = jsonObj.getString("message")
                            runOnUiThread {
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
        }
    }

    override fun setValues() {
    }
}