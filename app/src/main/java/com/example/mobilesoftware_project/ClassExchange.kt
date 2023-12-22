package com.example.mobilesoftware_project

import com.google.gson.annotations.SerializedName

/*
    환율 API에서 가져온 데이터를 저장하는 데 사용하는 클래스
 */

class dateExchange(
    val date: String,
    val results: List<ClassExchange>
)

data class ClassExchange (
    @SerializedName("cur_unit") val unit: String,
    //@SerializedName("ttb") val ttb : Double?,
    //@SerializedName("tts") val tts : Double?,
    @SerializedName("deal_bas_r") val deal : String?,
    @SerializedName("cur_nm") val name : String?
)
