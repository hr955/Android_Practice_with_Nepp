package com.example.colosseum_20210903.adatpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.colosseum_20210903.R
import com.example.colosseum_20210903.datas.ReplyData

class ChildReplyAdapter(val mContext: Context, resId: Int, val mList: List<ReplyData>) :
    ArrayAdapter<ReplyData>(mContext, resId, mList) {

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView
        if (row == null) {
            row = mInflater.inflate(R.layout.child_reply_list_adapter, null)
        }

        row!!

        val data = mList[position]

        val writerNicknameTxt = row.findViewById<TextView>(R.id.writerNicknameTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)

        return row
    }

}