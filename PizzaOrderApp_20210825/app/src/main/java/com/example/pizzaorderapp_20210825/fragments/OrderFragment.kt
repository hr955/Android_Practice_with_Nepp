package com.example.pizzaorderapp_20210825.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pizzaorderapp_20210825.R
import com.example.pizzaorderapp_20210825.StoreDetailActivity
import com.example.pizzaorderapp_20210825.adapters.StoreListViewAdapter
import com.example.pizzaorderapp_20210825.datas.StoreData
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : Fragment() {

    lateinit var mAdapter: StoreListViewAdapter

    private val mStoreList = arrayListOf<StoreData>(
        StoreData(
            "피자헛",
            "1588-5588",
            "https://www.pizzahut.co.kr",
            "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2FnkQca%2FbtqwVT4rrZo%2FymhFqW9uRbzrmZTxUU1QC1%2Fimg.jpg"
        ),
        StoreData(
            "파파존스",
            "1577-8080",
            "https://pji.co.kr/",
            "http://mblogthumb2.phinf.naver.net/20160530_65/ppanppane_1464617766007O9b5u_PNG/%C6%C4%C6%C4%C1%B8%BD%BA_%C7%C7%C0%DA_%B7%CE%B0%ED_%284%29.png?type=w800"
        ),
        StoreData(
            "미스터 피자",
            "1577-0077",
            "https://www.mrpizza.co.kr/",
            "https://post-phinf.pstatic.net/MjAxODEyMDVfMzYg/MDAxNTQzOTYxOTA4NjM3.8gsStnhxz7eEc9zpt5nmSRZmI-Pzpl4NJvHYU-Dlgmcg.7Vpgk0lopJ5GoTav3CUDqmXi2-_67S5AXD0AGbbR6J4g.JPEG/IMG_1641.jpg?type=w1200"
        ),
        StoreData(
            "도미노 피자",
            "1577-3082",
            "https://web.dominos.co.kr/",
            "https://pbs.twimg.com/profile_images/1098371010548555776/trCrCTDN_400x400.png"
        ),
        StoreData(
            "60계 치킨",
            "1566-3366",
            "http://www.60chicken.co.kr/",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnEbrBaRzN9FtdPgv-_RY5b-ojVfw4TLB3HA&usqp=CAU"
        ),
        StoreData(
            "네네 치킨",
            "1599-4479",
            "https://nenechicken.com/",
            "https://yt3.ggpht.com/ytc/AKedOLSDYyoxtErw7nZIBeQVVbthR8iOByMi5ViiYy8R3w=s900-c-k-c0x00ffffff-no-rj"
        ),
        StoreData(
            "본스 치킨",
            "1599-9992",
            "http://www.vons.or.kr/",
            "https://t1.daumcdn.net/cfile/tistory/26657F385887879F17"
        ),
        StoreData(
            "BHC 치킨",
            "1577-5577",
            "http://www.bhc.co.kr/",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6aozF-6sPnZNU_njFECA1WYozSytyIxKVoQ&usqp=CAU"
        ),
        StoreData(
            "롯데리아",
            "1600-9999",
            "http://www.lotteria.com/",
            "https://mblogthumb-phinf.pstatic.net/20160427_256/ppanppane_1461740417920Ka4kd_PNG/%B7%D4%B5%A5%B8%AE%BE%C6_%B7%CE%B0%ED_%282%29.png?type=w2"
        ),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mAdapter = StoreListViewAdapter(
            requireContext(),
            R.layout.fragment_order,
            mStoreList
        )
        storeListView.adapter = mAdapter

        storeListView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(requireContext(), StoreDetailActivity::class.java)
            intent.putExtra("storeData", mStoreList[position])
            startActivity(intent)
        }
    }
}