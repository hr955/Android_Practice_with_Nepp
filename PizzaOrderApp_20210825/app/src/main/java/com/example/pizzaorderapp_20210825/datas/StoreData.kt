package com.example.pizzaorderapp_20210825.datas

import java.io.Serializable

class StoreData(
    val storeName: String,
    val storeTel: String,
    val storeHomePageUrl: String,
    val storeLogoUrl: String
) : Serializable {
}