package com.example.pizzaorderapp_20210825

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.pizzaorderapp_20210825.base.BaseActivity
import com.example.pizzaorderapp_20210825.datas.StoreData
import kotlinx.android.synthetic.main.activity_store_detail.*
import kotlinx.android.synthetic.main.item_store_list.*
import kotlinx.android.synthetic.main.item_store_list.storeNameTxt

class StoreDetailActivity : BaseActivity() {

    private lateinit var mStoreData: StoreData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)

        setValues()
        setupEvents()
    }

    override fun setupEvents() {
        
    }


    override fun setValues() {
        mStoreData = intent.getSerializableExtra("storeData") as StoreData

        Glide.with(mContext).load(mStoreData.storeLogo).into(storeImg)
        storeNameTxt.text = mStoreData.storeName
        storeTelNum.text = mStoreData.storeTel
    }
}