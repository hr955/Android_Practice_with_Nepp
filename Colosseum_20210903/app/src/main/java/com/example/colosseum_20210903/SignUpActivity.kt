package com.example.colosseum_20210903

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
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
        //이메일 입력칸의 내용 변경 감지
        emailEdt.addTextChangedListener {
            // it 변수 -> 입력된 내용 파악

            // 변경되기만 하면 -> 검사결과 문구를 "중복 검사를 해주세요."로 변경
            checkEmailResultTxt.text = "중복 검사를 해주세요."
        }

        // 이메일 중복확인 버튼 클릭 이벤트
        checkEmailBtn.setOnClickListener {
            // 1. 입력한 이메일을 받아서
            val inputEmail = emailEdt.text.toString()

            // 2. 서버에 이메일 중복확인 요청 -> 응답에 따라, 결과 텍스트뷰의 문구 수정
            ServerUtil.getRequestDuplCheck(
                "EMAIL",
                inputEmail,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(jsonObj: JSONObject) {
                        val code = jsonObj.getInt("code")

                        runOnUiThread {
                            if (code == 200) {
                                checkEmailResultTxt.text = "사용해도 좋은 이메일입니다."

                            } else {
                                checkEmailResultTxt.text = "중복된 이메일입니다. 다른 이메일을 사용해주세요."
                            }
                        }
                    }
                })
        }

        // 회원가입 완료 버튼 클릭 이벤트
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
                            // 가입 성공 -> 이전 화면으로 복귀
                            runOnUiThread {
                                Toast.makeText(mContext, "가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
                                finish()
                            }
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