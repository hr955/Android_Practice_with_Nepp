package com.example.colosseum_20210903

import android.os.Bundle
import android.widget.Toast
import com.example.colosseum_20210903.datas.ReplyData
import com.example.colosseum_20210903.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_reply_detail.*
import org.json.JSONObject

class ViewReplyDetailActivity : BaseActivity() {

    lateinit var mReplyData: ReplyData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_reply_detail)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        addReplyBtn.setOnClickListener {
            // 최소 5자 이상 작성 (입력값 검증)
            val inputContent = contentEdt.text.toString()

            if (inputContent.length < 5) {
                Toast.makeText(mContext, "최소 5자 이상 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            ServerUtil.postRequestReplyToParentReply(
                mContext,
                inputContent,
                mReplyData.id,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(jsonObj: JSONObject) {
                        contentEdt.setText("")

                        // 리스트뷰 어댑터에게 변경사항 알려주고 새로고침
                    }
                })
        }
    }

    override fun setValues() {
        mReplyData = intent.getSerializableExtra("replyData") as ReplyData
        sideAndNicknameTxt.text = "(${mReplyData.selectedSide.title}) ${mReplyData.writer.nickname}"
        replyContentTxt.text = mReplyData.content
    }
}