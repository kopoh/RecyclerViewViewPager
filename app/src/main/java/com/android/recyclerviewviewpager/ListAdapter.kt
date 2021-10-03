package com.android.recyclerviewviewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.recyclerviewviewpager.data.GroupTable

class ListAdapter(private val list: List<GroupTable>) : RecyclerView.Adapter<TableViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TableViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val table: GroupTable = list[position]
        holder.bind(table)
    }

    override fun getItemCount(): Int = list.size
}

class TableViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
    private var mTimeView: TextView? = null
    private var mGroupView: TextView? = null
    private var mNameView: TextView? = null
    private var mSubjectView: TextView? = null
    private var mAuditoryView: TextView? = null

    init {
        mTimeView = itemView.findViewById(R.id.list_time)
        mGroupView = itemView.findViewById(R.id.list_group)
        mNameView = itemView.findViewById(R.id.list_name)
        mSubjectView = itemView.findViewById(R.id.list_subject)
        mAuditoryView = itemView.findViewById(R.id.list_description)
    }

    fun bind(table: GroupTable, /*int: raspInt, int: dayArrInt*/) {
        mTimeView?.text = table.raspisanie[0].dayArray[0].time
        mGroupView?.text = table.raspisanie[0].dayArray[0].group
        mNameView?.text = table.raspisanie[0].dayArray[0].teacherName
        mSubjectView?.text = table.raspisanie[0].dayArray[0].subject
        mAuditoryView?.text = table.raspisanie[0].dayArray[0].room
    }
}