package info.android.viewpager2app

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.recyclerviewviewpager.R
import com.android.recyclerviewviewpager.Table

class ListAdapter(private val list: List<Table>)
    : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val table: Table = list[position]
        holder.bind(table)
    }

    override fun getItemCount(): Int = list.size
}

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
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

    fun bind(table: Table) {
        mTimeView?.text = table.time
        mGroupView?.text = table.group
        mNameView?.text = table.name
        mSubjectView?.text = table.subject
        mAuditoryView?.text = table.auditory.toString()
    }
}