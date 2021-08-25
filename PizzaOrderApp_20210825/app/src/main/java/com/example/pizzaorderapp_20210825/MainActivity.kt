package com.example.pizzaorderapp_20210825

import android.os.Bundle
import com.example.pizzaorderapp_20210825.adapters.mainViewPagerAdapter
import com.example.pizzaorderapp_20210825.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var mvpa: mainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {


    }

    override fun setValues() {
        mvpa = mainViewPagerAdapter(supportFragmentManager)
        mainViewPager.adapter = mvpa
        mainTabLayout.setupWithViewPager(mainViewPager)
    }
}