package com.android.recyclerviewviewpager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class NumberAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 5
    private val TAG = "MainActivity"

    override fun createFragment(position: Int): Fragment {
        val fragment = MainFragment()
        Log.e(TAG, "I'm here $fragment")
        fragment.arguments = Bundle().apply {}
        return fragment
    }
}