package com.example.pizzaorderapp_20210825.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.pizzaorderapp_20210825.R
import com.example.pizzaorderapp_20210825.datas.StoreData

class StoreListViewAdapter(
    private val mContext: Context,
    resId: Int,
    private val mList: ArrayList<StoreData>
) :
    ArrayAdapter<StoreData>(mContext, resId, mList) {

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if (tempRow == null) {
            tempRow = mInflater.inflate(R.layout.item_store_list, null)
        }

        val row = tempRow!!
        val data = mList[position]

        val storeNameTxt = row.findViewById<TextView>(R.id.storeNameTxt)
        val storeLogoImg = row.findViewById<ImageView>(R.id.storeLogoImg)
        val hpBtn = row.findViewById<Button>(R.id.hpBtn)

        storeNameTxt.text = data.storeName
        Glide.with(mContext).load(data.storeLogoUrl).into(storeLogoImg)

        hpBtn.setOnClickListener {
            val hpUri = Uri.parse(data.storeHomePageUrl)

            mContext.startActivity(Intent(Intent.ACTION_VIEW, hpUri))
        }

        return row
    }
}