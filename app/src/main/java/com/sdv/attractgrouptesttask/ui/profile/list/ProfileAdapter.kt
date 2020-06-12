package com.sdv.attractgrouptesttask.ui.profile.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.petition.petition.ui.utils.DateFormatter
import com.sdv.attractgrouptesttask.R
import com.sdv.attractgrouptesttask.data.profile.Profile
import kotlinx.android.synthetic.main.profile_item_layout.view.*

class ProfileAdapter (private var listener: OnItemClickListener) :
    RecyclerView.Adapter<ProfileAdapter.PetitionViewHolder>(), Filterable {

    private var list: ArrayList<Profile> = ArrayList()
    private var listFilter: ArrayList<Profile> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetitionViewHolder {
        return PetitionViewHolder(
            parent
        )
    }

    override fun onBindViewHolder(holder: PetitionViewHolder, position: Int) {
        holder.bind(list[position], listener, position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setItems(listItems: ArrayList<Profile>) {
        list = listItems
        listFilter = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class PetitionViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) :
                this(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.profile_item_layout,
                        parent,
                        false
                    )
                )

        fun bind(model: Profile, listener: OnItemClickListener, position: Int) {
            itemView.tv_profile_title.text = model.name
            itemView.tv_profile_descr.text = model.description
            itemView.imgv_profile.load(model.image)
            itemView.tv_date_profile.text =  DateFormatter.fromISO8601UTC(model.time).toString()

            itemView.constraint_profile_item.setOnClickListener {
                listener.onItemClick(position)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    list = listFilter
                } else {
                    val resultList = ArrayList<Profile>()
                    for (row in listFilter) {
                        if (row.name.toLowerCase().contains(charSearch.toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    list = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = list
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                list = results?.values as ArrayList<Profile>
                notifyDataSetChanged()
            }
        }
    }

}