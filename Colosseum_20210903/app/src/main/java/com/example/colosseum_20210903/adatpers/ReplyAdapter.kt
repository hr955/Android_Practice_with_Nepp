package com.example.colosseum_20210903.adatpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.colosseum_20210903.R
import com.example.colosseum_20210903.ViewTopicDetailActivity
import com.example.colosseum_20210903.datas.ReplyData
import com.example.colosseum_20210903.utils.ServerUtil
import org.json.JSONObject

class ReplyAdapter(val mContext: Context, resId: Int, val mList: List<ReplyData>) :
    ArrayAdapter<ReplyData>(mContext, resId, mList) {

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView

        if (row == null) {
            row = mInflater.inflate(R.layout.reply_list_item, null)
        }

        row!!

        val data = mList[position]

        val selectedSideTxt = row.findViewById<TextView>(R.id.selectedSideTxt)
        val writerNicknameTxt = row.findViewById<TextView>(R.id.writerNicknameTxt)
        val createdAtTxt = row.findViewById<TextView>(R.id.createdAtTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)
        val replyCountTxt = row.findViewById<TextView>(R.id.replyCountTxt)
        val likeCountTxt = row.findViewById<TextView>(R.id.likeCountTxt)
        val hateCountTxt = row.findViewById<TextView>(R.id.hateCountTxt)

        contentTxt.text = data.content
        replyCountTxt.text = "답글 ${data.replyCount}개"
        likeCountTxt.text = "좋아요 ${data.likeCount}개"
        hateCountTxt.text = "싫어요 ${data.hateCount}개"
        selectedSideTxt.text = "(${data.selectedSide.title})"
        writerNicknameTxt.text = data.writer.nickname

        createdAtTxt.text = data.getFormattedTimeAgo()

        // 해당 댓글에 좋아오/싫어요 -> 서버에 전송

        likeCountTxt.tag = true
        hateCountTxt.tag = false

        val ocl = object : View.OnClickListener {
            override fun onClick(view: View?) {
                val isLike = view!!.tag.toString().toBoolean()

                ServerUtil.postRequestReplyLikeOrHate(
                    mContext,
                    data.id,
                    isLike,
                    object : ServerUtil.JsonResponseHandler {
                        override fun onResponse(jsonObj: JSONObject) {
                            (mContext as ViewTopicDetailActivity).getTopicDetailDataFromServer()
                        }
                    })
            }
        }

        likeCountTxt.setOnClickListener(ocl)
        hateCountTxt.setOnClickListener(ocl)

        return row
    }
}