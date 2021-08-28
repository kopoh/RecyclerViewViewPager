package com.android.recyclerviewviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class MainActivity : AppCompatActivity() {

    private var badge_position: Int = 3
    private val TAG = "MainActivity"
    private val layoutResId: Int
        @LayoutRes
        get() = R.layout.activity_main

    private lateinit var adapter: NumberAdapter
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

    private fun createFragment():Fragment = MainFragment.newInstance()

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

        adapter = NumberAdapter(this)
        viewPager = findViewById(R.id.pager)
        viewPager.adapter = adapter
        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.pager)

        // ensures fragments already created will not be created
        if (fragment == null) {
            //fragment = createFragment()
            // create and commit a fragment transaction
            //fm.beginTransaction()
                //.add(R.id.list_recycler_view, fragment)
                //.commit()
        }

        tabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNames[position]
            tab.setIcon(tabNumbers[position])

            if (position == badge_position) {
                val badge = tab.getOrCreateBadge()
                badge.number = 1
                //tab1.removeBadge()
            }

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab1: TabLayout.Tab?) {
                    // Handle tab select
                    if(position == badge_position){
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