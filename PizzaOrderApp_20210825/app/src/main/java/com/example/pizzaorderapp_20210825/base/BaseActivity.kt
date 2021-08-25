package com.example.pizzaorderapp_20210825.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    val mContext = this

    abstract fun setupEvents()

    abstract fun setValues()

}