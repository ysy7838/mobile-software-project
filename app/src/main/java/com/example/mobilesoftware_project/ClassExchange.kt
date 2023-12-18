package com.example.mobilesoftware_project
/*
    환율 API에서 가져온 데이터를 저장하는 데 사용하는 클래스
 */
//import com.google.gson.annotations.SerializedName
import java.time.LocalDate

class dateExchange(
    val date: LocalDate,
    //val results: MutableList<ClassExchange>
)
/*
data class ClassExchange (
    @SerializedName("result") val result : String,
    @SerializedName("unit") val cur_unit: String,
    @SerializedName("ttb") val ttb : Double?,
    @SerializedName("tts") val tts : Double?,
    @SerializedName("deal_bas_r") val deal : Double?,
    @SerializedName("cur_nm") val name : String?
)

 */