package com.example.numberbaseballgame_20210823

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import com.example.numberbaseballgame_20210823.adapters.MessageAdapter
import com.example.numberbaseballgame_20210823.datas.MessageData
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private val mMessageList = ArrayList<MessageData>()
    private lateinit var mAdapter: MessageAdapter

    private val mQuestionNumbers = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

            //CPU 응답 (?S ?B)
            checkAnswer(inputNumStr.toInt())
        }
    }

    //user 입력값과 답을 비교하는 함수
    private fun checkAnswer(inputNum: Int) {
        val userInputNumArr = ArrayList<Int>()
        userInputNumArr.add(inputNum / 100)
        userInputNumArr.add(inputNum % 100 / 10)
        userInputNumArr.add(inputNum % 10)

        var strikeCount = 0
        var ballCount = 0

        for (i in 0..2) {
            for (j in 0..2) {
                if (userInputNumArr[i] == mQuestionNumbers[j]) {
                    if (i == j) {
                        strikeCount++
                    } else {
                        ballCount++
                    }
                }
            }
        }
        //cpu 답변
        mMessageList.add(MessageData("${strikeCount}S ${ballCount}B", "CPU"))
        mAdapter.notifyDataSetChanged()
        messageListView.smoothScrollToPosition(mMessageList.size - 1)
    }

    //문제 생성 함수
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