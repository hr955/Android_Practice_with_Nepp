package com.example.colosseum_20210903

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.colosseum_20210903.utils.ContextUtil
import com.example.colosseum_20210903.utils.GlobalData
import kotlinx.android.synthetic.main.activity_my_profile.*

class MyProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

        // 로그아웃 버튼
        logoutBtn.setOnClickListener {
            // 로그아웃 -> 로그인시 세팅했던 데이터를 초기화
            // token, GlobalData.loginUser
            val alert = AlertDialog.Builder(mContext)
            alert.setMessage("정말 로그아웃 하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->
                // token 초기화 -> ContextUtil에 저장된 토큰값을 "" 로 설정
                ContextUtil.setToken(mContext,"")

                // GlobalData.loginUser 초기화
                GlobalData.loginUser = null

                // 모든화면 종료 -> 로그인화면으로 이동
                val myIntent = Intent(mContext, SignInActivity::class.java)
                // 모든 액티비티를 종료
                myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(myIntent)

                // finish() -> 현재 액티비티만 종료
            })
            alert.setNegativeButton("취소", null)
            alert.show()

        }
    }

    override fun setValues() {
    }
}