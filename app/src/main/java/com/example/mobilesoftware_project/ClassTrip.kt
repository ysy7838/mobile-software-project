package com.example.mobilesoftware_project

/*
    FireBase에서 가져온 데이터를 저장하는 데 사용하는 클래스
 */
class ClassTrip {

    val activity: MutableMap<String, Boolean> = mutableMapOf(
        "basic" to true,
        "bicycle" to false,
        "camping" to false,
        "hiking" to false,
        "photo" to false,
        "running" to false,
        "swimming" to false,
        "winterSports" to false,
        "work" to false
    )
    val sex: String = ""
    val destination: String = ""
    val isDomestic: Boolean = true
    val isInternational: Boolean = false
    val tripStart: String = ""
    val tripEnd: String = ""
    val haveChild: Boolean = false
}