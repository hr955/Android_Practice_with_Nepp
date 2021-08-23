package com.example.jickbangcopy_20210820.datas

import java.io.Serializable
import java.text.NumberFormat
import java.util.*
import kotlin.math.absoluteValue

class RoomData(
    private val price: Int,
    val address: String,
    private val floor: Int,
    val description: String
) : Serializable {
    fun convertFloor(): String = when {
        this.floor == 0 -> "반지하"
        this.floor < 0 -> "지하 ${this.floor.absoluteValue}층"
        else -> "${this.floor}층"
    }

    // 1(1만원) <= price < 100,000,000(1조원) 사이의 입력만 들어온다고 가정
    fun convertPrice(): String {
        if (this.price > 10000) {
            return "${NumberFormat.getNumberInstance(Locale.KOREA).format(this.price / 10000)}억 ${
                NumberFormat.getNumberInstance(Locale.KOREA).format(this.price % 10000)
            }"
        }
        return NumberFormat.getNumberInstance(Locale.KOREA).format(this.price)
    }
}

