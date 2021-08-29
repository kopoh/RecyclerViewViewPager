package com.android.recyclerviewviewpager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.recyclerviewviewpager.api.UserAPI
import com.android.recyclerviewviewpager.data.Table
import com.android.recyclerviewviewpager.databinding.FragmentMainBinding
import info.android.viewpager2app.ListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async

class TimeTableFragment : Fragment() {

    private val TAG : String = "MainActivity"

    private val timeTable = listOf(
        Table("08.45-10.15","ХУКО-02-20", 260, "Фика Турицин", "ШИЗИКА"),
        Table("10.30-12.00","ХИКО-01-20", 260, "Дора Раицин", "ШИЗИКА"),
        Table("12.40-14.10","КОКО-04-21", 260, "РАКСИМ МАМАШИН", "ФИЗ-РА"),
        Table("14.20-15.50","САКО-01-20", 260, "Жора Полицин", "ШИЗИКА"),
        Table("пиздец поздно","Хуегруппа", 1988, "все", "собрание у нас дура")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userApi = UserAPI()
        Log.d("!!!!!!!!!!!!!!!!!", "!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        val getTable = CoroutineScope(Dispatchers.IO).async {userApi.getTimeTable()}
        CoroutineScope(Dispatchers.IO).async {Log.e(TAG,getTable.await())}
        val binding = FragmentMainBinding.bind(view)
        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(timeTable)
        }

    }

    companion object {
        fun newInstance(): TimeTableFragment = TimeTableFragment()
    }
}