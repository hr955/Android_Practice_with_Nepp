package com.example.colosseum_20210903.adatpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.colosseum_20210903.R
import com.example.colosseum_20210903.datas.ReplyData

class ReplyAdapter(val mContext: Context, resId: Int, val mList: List<ReplyData>) :
    ArrayAdapter<ReplyData>(mContext, resId, mList) {

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView

        if(row == null){
            row = mInflater.inflate(R.layout.reply_list_item, null)
        }

        row!!

        return row
    }
}