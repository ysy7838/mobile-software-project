package com.example.mobilesoftware_project

/*
잘 동작하는지 확인을 위해 필요한 클래스
나중에 어떻게 연동하는지에 따라 수정될 수 있음
 */
data class Trip(val destination: String, val start: String, val end: String) {
    val tripPlace: String = destination
    val tripStart: String = start
    val tripEnd: String = end
}