package com.example.jickbangcopy_20210820

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jickbangcopy_20210820.datas.RoomData
import kotlinx.android.synthetic.main.activity_view_room_detail.*

class ViewRoomDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_room_detail)

        val roomData = intent.getSerializableExtra("roomData") as RoomData

        txt_price.text = roomData.convertPrice()
        txt_description.text = roomData.description
        txt_address.text = roomData.address
        txt_floor.text = roomData.convertFloor()
    }
}