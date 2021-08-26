package com.android.recyclerviewviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.recyclerviewviewpager.databinding.FragmentMainBinding
import info.android.viewpager2app.ListAdapter

data class Table(val time: String, val group: String, val auditory: Int, val name: String, val subject: String)

class MainFragment : Fragment() {
    private var fragmentNumberBinding: FragmentMainBinding? = null

    private val nicCageMovies = listOf(
        Table("08.45-10.15","ЩИКО-02-20", 260, "МЮРА ЮИЛИЦИН", "ШИЗИКА"),
        Table("10.30-12.00","ЩИКО-01-20", 260, "МЮРА ЮИЛИЦИН", "ШИЗИКА"),
        Table("12.40-14.10","ЩИКО-04-21", 260, "РАКСИМ МАМАШИН", "ФИЗ-РА"),
        Table("14.20-15.50","ЩАКО-01-20", 260, "МЮРА ЮИЛИЦИН", "ШИЗИКА"),
        Table("пиздец поздно","Хуегруппа", 1988, "все", "собрание у нас дура")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)
        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(nicCageMovies)
        }
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }
}