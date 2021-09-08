package com.example.colosseum_20210903.adatpers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.load.engine.Resource
import com.example.colosseum_20210903.R
import com.example.colosseum_20210903.ViewReplyDetailActivity
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

        // 내가 댓글에 좋아요/싫어요 찍은 여부를 판단 할 수 있음
        // data.myLike 등 변수를 활용
        if (data.myLike) {
            likeCountTxt.setBackgroundResource(R.drawable.red_border_rect)
            likeCountTxt.setTextColor(ContextCompat.getColor(mContext, R.color.like_red))
        } else {
            likeCountTxt.setBackgroundResource(R.drawable.black_border_rect)
            likeCountTxt.setTextColor(ContextCompat.getColor(mContext, R.color.black))

        }

        if (data.myHate) {
            hateCountTxt.setBackgroundResource(R.drawable.blue_border_rect)
            hateCountTxt.setTextColor(ContextCompat.getColor(mContext, R.color.hate_blue))
        } else {
            hateCountTxt.setBackgroundResource(R.drawable.black_border_rect)
            hateCountTxt.setTextColor(ContextCompat.getColor(mContext, R.color.black))
        }

        // 해당 댓글에 좋아요/싫어요 -> 서버에 전송

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

        replyCountTxt.setOnClickListener {
            val myIntent = Intent(mContext, ViewReplyDetailActivity::class.java)
            myIntent.putExtra("replyData", data)
            mContext.startActivity(myIntent)
        }

        return row
    }
}