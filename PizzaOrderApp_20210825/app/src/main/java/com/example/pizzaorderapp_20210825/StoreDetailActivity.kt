package com.example.pizzaorderapp_20210825

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.pizzaorderapp_20210825.base.BaseActivity
import com.example.pizzaorderapp_20210825.datas.StoreData
import kotlinx.android.synthetic.main.activity_store_detail.*
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
        dialBtn.setOnClickListener {
            val myUri = Uri.parse("tel:${mStoreData.storeTel}")
            startActivity(Intent(Intent.ACTION_DIAL, myUri))
        }
    }

    override fun setValues() {
        mStoreData = intent.getSerializableExtra("storeData") as StoreData

        Glide.with(mContext).load(mStoreData.storeLogoUrl).into(storeImg)
        storeNameTxt.text = mStoreData.storeName
        storeTelNum.text = mStoreData.storeTel
    }
}