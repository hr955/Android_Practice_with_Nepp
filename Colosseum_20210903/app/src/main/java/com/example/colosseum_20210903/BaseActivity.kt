package com.example.colosseum_20210903

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.math.MathContext

abstract class BaseActivity : AppCompatActivity() {
    lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this
    }

    abstract fun setupEvents()
    abstract fun setValues()

}