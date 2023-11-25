package com.example.mobilesoftware_project

/*
잘 동작하는지 확인을 위해 필요한 클래스
나중에 어떻게 연동하는지에 따라 수정될 수 있음
 */
class Trip(val place: String?, val period: String?) {
    var tripPlace: String? = place ?: "null"            // 비어있으면 "null" 문자열로 처리
    var tripPeriod: String? = period ?: "null"
}