package com.android.recyclerviewviewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.recyclerviewviewpager.data.GroupTable
import com.android.recyclerviewviewpager.data.TimeTable

val timeMap = mapOf(1 to "08.45-10.15", 2 to "10.30-12.00", 3 to "12.40-14.10", 4 to "14.20-15.50", 5 to "16.00-17.30", 6 to "17.40-19.10")

class ListAdapter(private val list: List<TimeTable>) : RecyclerView.Adapter<TableViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TableViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val table: TimeTable = list[position]
        holder.bind(table)
    }

    override fun getItemCount(): Int = list.size
}

class TableViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false))
{
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

    fun bind(table: TimeTable) {
        mTimeView?.text = timeMap[table.timeId]
        mGroupView?.text = table.group
        mNameView?.text = table.teacherName
        mSubjectView?.text = table.subject
        mAuditoryView?.text = table.room
    }
}