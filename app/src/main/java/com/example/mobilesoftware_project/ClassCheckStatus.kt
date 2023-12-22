package com.example.mobilesoftware_project

import java.io.Serializable

data class ClassCheckStatus(
    val id: String,
    var isChecked: Boolean
) : Serializable

data class ClassCheckTotal(
    val statusList: ArrayList<ClassCheckStatus>
) : Serializable