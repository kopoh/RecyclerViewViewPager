package com.android.recyclerviewviewpager.data

data class GroupTable(
    val groupId : Int = 1,
    var raspisanie : ArrayList<DayTable> = arrayListOf(DayTable())
)
