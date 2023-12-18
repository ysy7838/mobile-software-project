package com.example.mobilesoftware_project
/*
    환율 API에서 가져온 데이터를 저장하는 데 사용하는 클래스
 */
data class ClassExchange (
    val cur_unit: String = "",
    val cur_un: String = "",
    val ttb: String = "",
    val tts: String = "",
    val deal_bas_r: String = "",
)