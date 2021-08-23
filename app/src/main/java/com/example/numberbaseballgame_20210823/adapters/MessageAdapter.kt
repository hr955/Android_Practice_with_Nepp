package com.example.numberbaseballgame_20210823.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.numberbaseballgame_20210823.R
import com.example.numberbaseballgame_20210823.datas.MessageData

class MessageAdapter(
    mContext: Context,
    resId: Int,
    private val mList: ArrayList<MessageData>
) : ArrayAdapter<MessageData>(mContext, resId, mList) {

    var mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if(tempRow == null){
            tempRow = mInflater.inflate(R.layout.message_list_item, null)
        }

        var row = tempRow!!

       val data = mList[position]

        return row
    }
}