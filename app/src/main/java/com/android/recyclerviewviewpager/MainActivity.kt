package com.android.recyclerviewviewpager

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.android.recyclerviewviewpager.data.GroupTable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var badgePosition: Int = 3
    private val TAG = "MainActivity"
    private val layoutResId: Int
        @LayoutRes
        get() = R.layout.activity_main

    private lateinit var adapter: TimeTableAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    private val tabNames: Array<String> = arrayOf(
        "Пн",
        "Вт",
        "Ср",
        "Чт",
        "Пт"
    )
    private val tabNumbers: Array<Int> = arrayOf(
        R.drawable.baseline_looks_one_black_48,
        R.drawable.baseline_looks_two_black_48,
        R.drawable.baseline_looks_3_black_48,
        R.drawable.baseline_looks_4_black_48,
        R.drawable.baseline_looks_5_black_48
    )

    override fun onResume() {
        super.onResume()
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)
        if (day >= 7) {
            viewPager.currentItem = 0
        } else
            viewPager.currentItem = day - 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        adapter = TimeTableAdapter(this)
        viewPager = findViewById(R.id.pager)
        viewPager.adapter = adapter

        /*refreshPager.setOnRefreshListener {
            // Initialize a new Runnable
            runnable = Runnable {

                val userApi = UserAPI()
                val getTable = CoroutineScope(Dispatchers.IO).async {userApi.getTimeTable()}
                CoroutineScope(Dispatchers.IO).async {Log.e(TAG,getTable.await())}

                // Hide swipe to refresh icon animation
                refreshPager.isRefreshing = false
            }

            // Execute the task after specified time
            handler.postDelayed(
                runnable, 3000.toLong()
            )
        }*/



        tabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNames[position]
            tab.setIcon(tabNumbers[position])

            if (position == badgePosition) {
                val badge = tab.orCreateBadge
                badge.number = 1
                //tab1.removeBadge()
            }

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab1: TabLayout.Tab?) {
                    // Handle tab select
                    if (position == badgePosition) {
                        tab1?.removeBadge()
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    println(tab)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    println(tab)
                }
            })

            val calendar: Calendar = Calendar.getInstance()
            val day: Int = calendar.get(Calendar.DAY_OF_WEEK)
            if (day >= 6) {
                viewPager.currentItem = 0
            } else
                viewPager.currentItem = day - 2
            Log.e(TAG, day.toString())
        }.attach()

    }
}