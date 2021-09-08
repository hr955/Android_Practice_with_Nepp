package com.example.colosseum_20210903.adatpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.colosseum_20210903.R
import com.example.colosseum_20210903.datas.NotificationData
import java.text.SimpleDateFormat

class NotificationAdapter(val mContext: Context, resId: Int, val mList: List<NotificationData>) :
    ArrayAdapter<NotificationData>(mContext, resId, mList) {

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView
        if (row == null) {
            row = mInflater.inflate(R.layout.notification_list_item, null)
        }

        row!!

        val data = mList[position]
        val notiTitleTxt = row.findViewById<TextView>(R.id.notiTitleTxt)
        val createdAtTxt = row.findViewById<TextView>(R.id.createdAtTxt)

        notiTitleTxt.text = data.title
        // Calendar -> String으로 가공
        val sdf = SimpleDateFormat("yyyy년 M월 d일 a h:mm")
        createdAtTxt.text = sdf.format( data.createdAt.time )

        return row
    }
}