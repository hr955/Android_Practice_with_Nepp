package com.example.colosseum_20210903

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.colosseum_20210903.datas.UserData
import com.example.colosseum_20210903.utils.ContextUtil
import com.example.colosseum_20210903.utils.GlobalData
import com.example.colosseum_20210903.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.json.JSONObject

class SignInActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        // 자동 로그인 체크박스의 값이 바뀔때마다 저장
        autoLoginCheckBox.setOnCheckedChangeListener { compoundButton, isChecked ->
            // isChecked에 들어오는 값을 저장
            ContextUtil.setAutoLogin(mContext, isChecked)
        }

        // 회원가입 버튼 클릭 이벤트
        signUpBtn.setOnClickListener {
            startActivity(Intent(mContext, SignUpActivity::class.java))
        }

        // 로그인 버튼 클릭 이벤트
        signInBtn.setOnClickListener {
            //입력한 아이디와 비번을 변수로 저장 -> 서버에서 일치여부 판단
            val inputId = emailEdt.text.toString()
            val inputPw = passwordEdt.text.toString()

            // 서버에 이 데이터가 존재하는지 확인요청 -> 로그인 시도
            // 서버 로그인 시도 -> 서버에 다녀오면 어떡할건지? 대응 가이드북 변수로 첨부 (인터페이스 객체)
            ServerUtil.postRequestSignIn(inputId, inputPw, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {
                    // 서버가 보내준 jsonObj를 가지고 처리하는 코드 작성 영역
                    //Log.d("화면에서 받은 JSON", jsonObj.toString())

                    // "code" 이름표가 붙은 Int값 추출
                    val code = jsonObj.getInt("code")
                    Log.d("코드값", code.toString())

                    // 원하는 의도대로 잘 동작 -> code : 200
                    // 어떤 이유든 에러가 있다 -> code : 200이 아닌 값
                    if (code == 200) {
                        // 정상 작동한 경우 -> 로그인 성공
                        // 로그인 성공시 토스트 띄우기
                        // "data" 이름의 { } 를 변수로 담자
//                        val dataObj = jsonObj.getJSONObject("data")
//                        // data: {} 안에서 user:{}를 변수에 담자
//                        val userObj = dataObj.getJSONObject("user")
//                        val nickname = userObj.getString("nick_name")
//                        runOnUiThread {
//                            Toast.makeText(mContext, "${nickname}님 환영합니다", Toast.LENGTH_SHORT)
//                                .show()
//                        }

                        // 서버가 내려주는 토큰값을 기기에 저장 (ContextUtil 활용)
                        val dataObj = jsonObj.getJSONObject("data")
                        val token = dataObj.getString("token")

                        // 로그인시 닉네임 GlobalData에 추가
                        val userObj = dataObj.getJSONObject("user")
                        GlobalData.loginUser = UserData.getUserDataFromJson(userObj)

                        ContextUtil.setToken(mContext, token)

                        startActivity(Intent(mContext, MainActivity::class.java))
                        finish()
                    } else {
                        // 로그인 실패 -> 토스트를 띄워보자
                        // 백그라운드에서 서버통신 중 -> UI에 토스트를 띄운다 -> 다른 쓰레드가 UI조작 (위험요소)
                        // 서버가 알려주는 로그인 실패사유도 파싱. 토스트의 내용으로 띄워주자.
                        val message = jsonObj.getString("message")

                        runOnUiThread {
                            // UI 조작은 UI 쓰레드에게 일을 따로 맡겨주자
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    override fun setValues() {
        // 자동로그인 여부를 받아내서 -> 자동 로그인 체크박스에 반영
        autoLoginCheckBox.isChecked = ContextUtil.getAutoLogin(mContext)
    }
}