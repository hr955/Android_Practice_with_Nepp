package com.example.jickbangcopy_20210820.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.jickbangcopy_20210820.R
import com.example.jickbangcopy_20210820.datas.RoomData

class RoomAdapter(
        mContext: Context,
        resId: Int,
        private val mList: ArrayList<RoomData>
) : ArrayAdapter<RoomData>(mContext, resId, mList) {

    var mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if(tempRow == null){
            tempRow = mInflater.inflate(R.layout.item_room_list, null)
        }

        var row = tempRow!!

        val txtPrice = row.findViewById<TextView>(R.id.txt_price)
        val txtAddress = row.findViewById<TextView>(R.id.txt_address)
        val txtDescription = row.findViewById<TextView>(R.id.txt_description)

        val data = mList[position]

//        txtPrice.text = data.price.toString()
        txtPrice.text = "${data.convertPrice()}"
        txtAddress.text = "${data.address}, ${data.convertFloor()}"
        txtDescription.text = data.description

        return row
    }
}