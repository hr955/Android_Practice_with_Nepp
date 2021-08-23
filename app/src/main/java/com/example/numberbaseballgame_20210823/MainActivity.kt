package com.example.numberbaseballgame_20210823

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numberbaseballgame_20210823.adapters.MessageAdapter
import com.example.numberbaseballgame_20210823.datas.MessageData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val mMessageList = ArrayList<MessageData>()
    private lateinit var mAdapter: MessageAdapter

    private val mQuestionNumbers = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //난수 생성 함수
        makeQuestionNumbers()

        mAdapter = MessageAdapter(this, R.layout.message_list_item, mMessageList)
        messageListView.adapter = mAdapter

        sendButton.setOnClickListener {
            val inputNumStr = numberEdt.text.toString()
            val msg = MessageData(inputNumStr, "USER")

            mMessageList.add(msg)
            mAdapter.notifyDataSetChanged()

            numberEdt.setText("")

            //리스트뷰 최하단으로 이동
            messageListView.smoothScrollToPosition(mMessageList.size - 1)
        }
    }

    private fun makeQuestionNumbers() {
        mQuestionNumbers.add(4)
        mQuestionNumbers.add(7)
        mQuestionNumbers.add(1)

        //환영메세지 채팅창에 띄우기
        mMessageList.add(MessageData("어서오세요", "CPU"))
        mMessageList.add(MessageData("숫자야구 게임입니다.", "CPU"))
        mMessageList.add(MessageData("세자리 숫자를 맞춰주세요", "CPU"))

    }
}