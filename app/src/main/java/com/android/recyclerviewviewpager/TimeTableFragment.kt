package com.android.recyclerviewviewpager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.recyclerviewviewpager.api.UserAPI
import com.android.recyclerviewviewpager.data.DayTable
import com.android.recyclerviewviewpager.data.GroupTable
import com.android.recyclerviewviewpager.data.TimeTable
import com.android.recyclerviewviewpager.databinding.TimetableFragmentBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class TimeTableFragment : Fragment() {

    private val TAG: String = "TimeTableFragment"


    private var timeTable = listOf(
        GroupTable(1,
            arrayListOf(
            DayTable(1,
                arrayListOf(
                    TimeTable(1, "08.45-10.15", "ХУКО-021-20", "Фика Турицин", "ШИЗИКА", "260"),
                    TimeTable(2, "10.30-12.00", "ХУКО-021-20", "Фика Турицин", "ШИЗИКА", "260"),
                    TimeTable(3, "12.40-14.10", "ХУКО-021-20", "Фика Турицин", "ШИЗИКА", "260"),
                    TimeTable(4, "12.40-14.10", "ХУКО-021-20", "Фика Турицин", "ШИЗИКА", "260"),
                    TimeTable(5, "12.40-14.10", "ХУКО-021-20", "Фика Турицин", "ШИЗИКА", "260"),
                    TimeTable(6, "14.20-15.50", "ХУКО-021-20", "Фика Турицин", "ШИЗИКА", "260")
                )),
            )
        ),
    )

    private val mutableListTable = mutableListOf(
        TimeTable()
    )

    private var timeTable1 = listOf(
        TimeTable(1, "08.45-10.15", "ХУКО-021-20", "Фика Турицин", "ШИЗИКА", "260"),
        TimeTable(2, "10.30-12.00", "ХИКО-01-20", "Дора Раицин", "ШИЗИКА", "260"),
        TimeTable(3, "12.40-14.10", "КОКО-04-21", "РАКСИМ МАМАШИН", "ФИЗ-РА", "260"),
        TimeTable(4, "14.20-15.50", "САКО-01-20", "Жора Полицин", "ШИЗИКА", "260"),
        TimeTable(5, "очень поздно", "нет группы, только Автоботы", "все", "собрание у нас дура", "260")
    )


    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.timetable_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = TimetableFragmentBinding.bind(view)

        binding.swipeRefresher.setOnRefreshListener {
            val gson = Gson()
            val userApi = UserAPI()
            val getTable = CoroutineScope(Dispatchers.IO).async { userApi.getTimeTable() }

            /*binding.listRecyclerView.apply {
                val jsonString = gson.toJson(timeTable)    // make JSON from timetable in that class
                val sType =
                    object : TypeToken<List<GroupTable>>() {}.type  //type of structure in our JSON
                val dataTimeTable = gson.fromJson<List<GroupTable>>(
                    jsonString,
                    sType
                )  //our JSON to something normal

                Log.e(TAG, "Я ЗДЕСЬЬЬЬЬ$dataTimeTable")  //debug fun

                layoutManager = LinearLayoutManager(activity)  //creates activity

                mutableListTable.clear()   //mutableList cleared :)
                (0..5).forEach { //make 6 subjects from JSON
                    mutableListTable.add(dataTimeTable[0].raspisanie[0].dayArray[it]) // magic numbers, it's all 0, that is first day in GroupTable I make something with this structure
                    Log.w(TAG, "$mutableListTable")
                    adapter =
                        ListAdapter(mutableListTable) //magic numbers is OK? No that is ЗАГЛУШКА
                }
            }
            // reset the SwipeRefreshLayout (stop the loading spinner)
            binding.swipeRefresher.isRefreshing = false*/


            CoroutineScope(Dispatchers.IO).async { // refresh your list contents somehow
                val json = getTable.await()  // POST or GET receive data

                binding.listRecyclerView.apply {
                    val jsonString =
                        gson.toJson(json)  //временное решение, можно обойтись и без этой строчки
                    val sType = object :
                        TypeToken<List<GroupTable>>() {}.type  //type of structure in our JSON
                    val dataTimeTable = gson.fromJson<List<GroupTable>>(
                        json,
                        sType
                    )  //our JSON to something normal

                    Log.e(TAG, "Я ЗДЕСЬЬЬЬЬ$jsonString")  //debug fun

                    layoutManager = LinearLayoutManager(activity)  //creates activity

                    mutableListTable.clear()   //mutableList cleared :)
                    (0..5).forEach { //make 6 subjects from JSON
                        mutableListTable.add(dataTimeTable[0].raspisanie[0].dayArray[it]) // magic numbers, it's all 0, that is first day in GroupTable I make something with this structure
                        Log.w(TAG, "$mutableListTable")
                        adapter =
                            ListAdapter(mutableListTable) //magic numbers is OK? No that is ЗАГЛУШКА
                    }
                }
                // reset the SwipeRefreshLayout (stop the loading spinner)
                binding.swipeRefresher.isRefreshing = false
            }
        }

        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(timeTable1) //default data on start
        }
    }
}



        /*val userApi = UserAPI()
        val getTable = CoroutineScope(Dispatchers.IO).async {userApi.getTimeTable()}
        CoroutineScope(Dispatchers.IO).async {Log.e(TAG,getTable.await())}*/