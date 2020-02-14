package com.szabgab.findalike

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.szabgab.findalike.databinding.TitleItemBinding

class RecyclerViewAdapter(private val suggestedTitlesDataList: ArrayList<TitleData>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(suggestedTitlesDataList[position], this)
    }

//    override fun getItemId(position: Int): Long {
//        return super.getItemId(position)
//        return suggestedTitlesDataList[position].imdbID // TODO maybe hash this to a long?
//    }

    override fun getItemCount() = suggestedTitlesDataList.size

    class ViewHolder private constructor (val binding: TitleItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindItems(titleData : TitleData, adapter: RecyclerViewAdapter){
            binding.titleData = titleData
            binding.executePendingBindings()

            binding.posterTitleAndCheckboxLayout.setOnClickListener {
                titleData.isLayoutExpanded = !titleData.isLayoutExpanded
                adapter.notifyItemChanged(adapterPosition)
            }

            binding.markAsSeenCheckBox.setOnClickListener {
                val checkBoxStatus = binding.markAsSeenCheckBox.isChecked
                titleData.isSeen = checkBoxStatus // todo send to DB
                // markAsSeenCheckBox.isChecked = checkBoxStatus
                adapter.notifyItemChanged(adapterPosition)

            }

            if (titleData.isLayoutExpanded) {
                binding.expandableLayout.visibility = View.VISIBLE
            } else {
                binding.expandableLayout.visibility = View.GONE
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TitleItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}