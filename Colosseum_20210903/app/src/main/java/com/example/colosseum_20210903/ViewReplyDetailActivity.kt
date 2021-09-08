package com.example.colosseum_20210903

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.colosseum_20210903.adatpers.ChildReplyAdapter
import com.example.colosseum_20210903.datas.ReplyData
import com.example.colosseum_20210903.datas.TopicData
import com.example.colosseum_20210903.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_reply_detail.*
import org.json.JSONObject

class ViewReplyDetailActivity : BaseActivity() {

    lateinit var mReplyData: ReplyData
    var mChildReplyData = ArrayList<ReplyData>()
    lateinit var mChildReplyAdapter: ChildReplyAdapter

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

                        // 키보드 숨김처리
                        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                    }
                })
        }
    }

    override fun setValues() {
        mReplyData = intent.getSerializableExtra("replyData") as ReplyData
        sideAndNicknameTxt.text = "(${mReplyData.selectedSide.title}) ${mReplyData.writer.nickname}"
        replyContentTxt.text = mReplyData.content

        ServerUtil.getRequestChildReplyList(mContext, mReplyData.id, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {
                super.onResponse(jsonObj)

                // 서버에서 댓글목록 불러와 List 채워주기
                val dataObj = jsonObj.getJSONObject("data")
                val replyObj = dataObj.getJSONObject("reply")
                val childReplyArr = replyObj.getJSONArray("replies")

                for (i in 0 until childReplyArr.length()) {
                    val childReplyObj = childReplyArr.getJSONObject(i)

                    mChildReplyData.add(ReplyData.getReplyDataFromJson(childReplyObj))
                }

                runOnUiThread {
                    mChildReplyAdapter.notifyDataSetChanged()
                }
            }
        })

        mChildReplyAdapter = ChildReplyAdapter(mContext, R.layout.child_reply_list_adapter, mChildReplyData)
        ChildReplyListView.adapter = mChildReplyAdapter

    }
}