package com.android.recyclerviewviewpager.data

data class GroupTable(
    val groupId : Int = 1,
    val groupName : String = "",
    var raspisanie : ArrayList<DayTable> = arrayListOf(DayTable())
)
