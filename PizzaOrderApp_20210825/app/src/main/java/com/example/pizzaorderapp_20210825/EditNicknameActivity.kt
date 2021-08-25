package com.example.pizzaorderapp_20210825

import android.app.Activity
import android.os.Bundle
import com.example.pizzaorderapp_20210825.base.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_nickname.*

class EditNicknameActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_nickname)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        endEdtBtn.setOnClickListener {
            intent.putExtra("Nickname", newNicknameEtd.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun setValues() {
    }
}