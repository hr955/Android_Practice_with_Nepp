package com.example.pizzaorderapp_20210825.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.pizzaorderapp_20210825.R
import com.example.pizzaorderapp_20210825.datas.StoreData

class StoreListViewAdapter(
    private val mContext: Context,
    private val resId: Int,
    private val mList: ArrayList<StoreData>
) :
    ArrayAdapter<StoreData>(mContext, resId, mList) {

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if (tempRow == null) {
            tempRow = mInflater.inflate(R.layout.item_store_list, null)
        }

        var row = tempRow!!

        val storeNameTxt = row.findViewById<TextView>(R.id.storeNameTxt)
        val storeLogoImg = row.findViewById<ImageView>(R.id.storeLogoImg)

        return row
    }
}