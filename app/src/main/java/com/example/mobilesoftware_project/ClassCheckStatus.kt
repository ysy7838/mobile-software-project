package com.example.mobilesoftware_project

data class ClassCheckStatus(
    val id: String,
    var isChecked: Boolean
)

data class ClassCheckTotal(
    val statusList: ArrayList<ClassCheckStatus>
)