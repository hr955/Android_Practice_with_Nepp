package com.example.colosseum_20210903

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.colosseum_20210903.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

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
                    } else {
                        // 로그인 실패 -> 토스트를 띄워보자
                        // 백그라운드에서 서버통신 중 -> UI에 토스트를 띄운다 -> 다른 쓰레드가 UI조작 (위험요소)
                        runOnUiThread {
                            // UI 조작은 UI 쓰레드에게 일을 따로 맡겨주자
                            Toast.makeText(mContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    override fun setValues() {
    }
}