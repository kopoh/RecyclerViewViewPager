package com.android.recyclerviewviewpager

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.recyclerviewviewpager.api.UserAPI
import com.android.recyclerviewviewpager.data.DayTable
import com.android.recyclerviewviewpager.data.GroupTable
import com.android.recyclerviewviewpager.data.TimeTable
import com.android.recyclerviewviewpager.databinding.TimetableFragmentBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class TimeTableFragment : Fragment() {

    private val TAG: String = "TimeTableFragment"
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable


    private var timeTable = listOf(
        GroupTable(1, "SHIKO-02-20", arrayListOf(DayTable(1, arrayListOf(TimeTable(1, "08.45-10.15", "ХУКО-021-20", "Фика Турицин", "ШИЗИКА", "260"))),DayTable(2, arrayListOf(TimeTable(2, "10.30-12.00", "ХУКО-02-20", "Фика Турицин", "ШИЗИКА", "260"))))),
        GroupTable(1, "SHIKO-02-20", arrayListOf(DayTable(2, arrayListOf(TimeTable(1, "08.45-dfkfjdf10.15", "ХУКО-021-20", "Фика Турицин", "ШИЗИКА", "260"))),DayTable(2, arrayListOf(TimeTable(2, "10.30-12.00", "ХУКО-02-20", "Фика Турицин", "ШИЗИКА", "260"))))),
        GroupTable(1, "SHIKO-02-20", arrayListOf(DayTable(3, arrayListOf(TimeTable(1, "08.45-10.15", "ХУКО-021-20", "Фика Турицин", "ШИЗИКА", "260"))),DayTable(2, arrayListOf(TimeTable(2, "10.30-12.00", "ХУКО-02-20", "Фика Турицин", "ШИЗИКА", "260"))))),
        GroupTable(1, "SHIKO-02-20", arrayListOf(DayTable(4, arrayListOf(TimeTable(1, "08.45-10.15", "ХУКО-021-20", "Фика Турицин", "ШИЗИКА", "260"))),DayTable(2, arrayListOf(TimeTable(2, "10.30-12.00", "ХУКО-02-20", "Фика Турицин", "ШИЗИКА", "260"))))),
        GroupTable(1, "SHIKO-02-20", arrayListOf(DayTable(5, arrayListOf(TimeTable(1, "08.45-10.15", "ХУКО-021-20", "Фика Турицин", "ШИЗИКА", "260"))),DayTable(2, arrayListOf(TimeTable(2, "10.30-12.00", "ХУКО-02-20", "Фика Турицин", "ШИЗИКА", "260")))))
//GroupTable(1,"111")
    )

                            /*private var timeTable = listOf(
        DayTable(1, 1, "08.45-10.15", "ХУКО-02-20", "Фика Турицин", "ШИЗИКА", "260"),
        DayTable(1, 2, "10.30-12.00", "ХИКО-01-20", "Дора Раицин", "ШИЗИКА", "260"),
        DayTable(1, 3, "12.40-14.10", "КОКО-04-21", "РАКСИМ МАМАШИН", "ФИЗ-РА", "260"),
        DayTable(1, 4, "14.20-15.50", "САКО-01-20", "Жора Полицин", "ШИЗИКА", "260"),
        DayTable(1, 5, "очень поздно", "нет группы, только Автоботы", "все", "собрание у нас дура", "260")
    )*/


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.timetable_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = TimetableFragmentBinding.bind(view)

        binding.swipeRefresher.setOnRefreshListener {
            val gson = Gson()
            val userApi = UserAPI()
            val getTable = CoroutineScope(Dispatchers.IO).async { userApi.getTimeTable() }

            CoroutineScope(Dispatchers.IO).async { // refresh your list contents somehow
                val json = getTable.await()
                val dataTimeTable = gson.fromJson(json, DayTable::class.java)

                Log.d(TAG, "Я ЗДЕСЬЬЬЬЬ$dataTimeTable + $json")

                binding.listRecyclerView.apply {
                    layoutManager = LinearLayoutManager(activity)

                    adapter = ListAdapter(timeTable)
                }

                // reset the SwipeRefreshLayout (stop the loading spinner)
                binding.swipeRefresher.isRefreshing = false
            }
        }

        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(timeTable)
        }
    }
}



        /*val userApi = UserAPI()
        val getTable = CoroutineScope(Dispatchers.IO).async {userApi.getTimeTable()}
        CoroutineScope(Dispatchers.IO).async {Log.e(TAG,getTable.await())}*/