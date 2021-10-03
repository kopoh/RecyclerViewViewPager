package com.android.recyclerviewviewpager.data

data class DayTable(
    val dayId : Int = 1,
    var dayArray : ArrayList<TimeTable> = arrayListOf(TimeTable())
)