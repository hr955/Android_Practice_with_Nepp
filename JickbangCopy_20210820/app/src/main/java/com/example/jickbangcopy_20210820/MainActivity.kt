package com.example.jickbangcopy_20210820

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jickbangcopy_20210820.adapter.RoomAdapter
import com.example.jickbangcopy_20210820.datas.RoomData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val roomList = arrayListOf<RoomData>(
                RoomData(8000, "서울시 동대문구", 0, "풀옵션 넓은 방"),
                RoomData(12000, "서울시 강남구", 5, "해가 잘 드는 집"),
                RoomData(500, "서울시 은평구", -1, "신혼부부 추천 매물"),
                RoomData(8050, "경기도 분당시", 7, "베란다 공간 있는 방"),
                RoomData(85008050, "인천시 부평구", 5, "부평역 도보 5분"),
                RoomData(350000, "경기도 의정부시", 12, "베란다 공간 있는 방"),
                RoomData(350010, "경기도 의정부시", 35, "고층 매물"),
                RoomData(99999999, "한남동 유앤빌리지~", 4, "언덕에서 달을 보면~")
        )

        lv_room_list.adapter = RoomAdapter(this, R.layout.item_room_list, roomList)

        lv_room_list.setOnItemClickListener { parent, view, position, id ->
            val clickedRoom = roomList[position]
            val myIntent = Intent(this,ViewRoomDetailActivity::class.java)
            myIntent.putExtra("roomData", clickedRoom)
            startActivity(myIntent)
        }
    }
}