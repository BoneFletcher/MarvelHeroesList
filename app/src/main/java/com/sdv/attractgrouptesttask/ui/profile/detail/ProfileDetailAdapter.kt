package com.sdv.attractgrouptesttask.ui.profile.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.petition.petition.ui.utils.DateFormatter
import com.sdv.attractgrouptesttask.R
import com.sdv.attractgrouptesttask.data.profile.Profile
import kotlinx.android.synthetic.main.profile_detail_item_layout.view.*

class ProfileDetailAdapter :
    androidx.recyclerview.widget.ListAdapter<Profile, ProfileDetailAdapter.PetitionViewHolder>(
        NotificationPetitionItemDiffCallback()
    ) {
    var list: List<Profile> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetitionViewHolder {
        return PetitionViewHolder(
            parent
        )
    }

    override fun onBindViewHolder(holder: PetitionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface OnItemClickListener {
        fun onItemClick(model: Profile)
    }

    fun clearAdapter() {
        submitList(list)
    }

    class PetitionViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) :
                this(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.profile_detail_item_layout,
                        parent,
                        false
                    )
                )

        fun bind(model: Profile) {
            itemView.tv_title_profile.text = model.name
            itemView.tv_descr_profile.text = model.description
            itemView.tv_date_profile.text =  DateFormatter.fromISO8601UTC(model.time).toString()
            itemView.imgv_profile_photo.load(model.image)
        }
    }


    class NotificationPetitionItemDiffCallback : DiffUtil.ItemCallback<Profile>() {
        override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean =
            oldItem == newItem
    }
}