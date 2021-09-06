package com.example.colosseum_20210903.datas

import java.io.Serializable

class TopicData(
    var id: Int,
    var title: String,
    var imageUrl: String
) : Serializable {
    // 진영 목록을 담아줄 ArrayList
    val sideList = ArrayList<SideData>()

    // 보조 생성자 추가
    constructor() : this(0, "제목없음", "")

    // id 값만 받는 보조 생성자
    constructor(id: Int) : this(id, "제목없음", "")

}