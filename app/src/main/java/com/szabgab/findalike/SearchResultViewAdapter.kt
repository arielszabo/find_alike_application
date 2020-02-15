package com.szabgab.findalike

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView
import com.szabgab.findalike.databinding.TitleItemBinding

class SearchResultViewAdapter(private val suggestedTitlesDataList: ArrayList<TitleData>): RecyclerView.Adapter<SearchResultViewAdapter.TitleItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TitleItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: TitleItemViewHolder, position: Int) {
        holder.bindItems(suggestedTitlesDataList[position], this)
    }

    override fun getItemId(position: Int): Long {
//        return super.getItemId(position)
        val imdbID = suggestedTitlesDataList[position].imdbID
        return imdbID.substring(2).toLong()
    }

    override fun getItemCount() = suggestedTitlesDataList.size

    class TitleItemViewHolder private constructor (val binding: TitleItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindItems(titleData : TitleData, adapter: SearchResultViewAdapter){
            binding.titleData = titleData
            binding.executePendingBindings()

            binding.posterTitleAndCheckboxLayout.setOnClickListener {
                titleData.isLayoutExpanded = !titleData.isLayoutExpanded
                adapter.notifyItemChanged(adapterPosition) // TODO use DiffUtil
            }

            binding.markAsSeenCheckBox.setOnClickListener {
                val checkBoxStatus = binding.markAsSeenCheckBox.isChecked
                titleData.isSeen = checkBoxStatus // todo send to DB
                // markAsSeenCheckBox.isChecked = checkBoxStatus
                adapter.notifyItemChanged(adapterPosition) // TODO use DiffUtil

            }

            if (titleData.isLayoutExpanded) {
                binding.expandableLayout.visibility = View.VISIBLE
            } else {
                binding.expandableLayout.visibility = View.GONE
            }
        }

        companion object {
            fun from(parent: ViewGroup): TitleItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TitleItemBinding.inflate(layoutInflater, parent, false)
                return TitleItemViewHolder(binding)
            }
        }
    }
}


class SearchResultViewListener(val clickListener: (imdbID: String) -> Unit) {
    fun onClick(titleData: TitleData) = clickListener(titleData.imdbID)
}