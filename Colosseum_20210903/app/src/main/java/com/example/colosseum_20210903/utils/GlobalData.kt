package com.example.colosseum_20210903.utils

import com.example.colosseum_20210903.datas.UserData

class GlobalData {
    companion object {
        // 로그인한 사용자 정보를 담아둘 변수
        // 앱이 처음 켜졌을때는 사용자정보가 없다
        var loginUser: UserData? = null

    }
}