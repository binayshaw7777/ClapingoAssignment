package com.binayshaw7777.clapingoassignemnt.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.binayshaw7777.clapingoassignemnt.R
import com.binayshaw7777.clapingoassignemnt.databinding.SlotsItemLayoutBinding

private var slotItemList: MutableList<String> = ArrayList()
private var bookedSlotItemList: MutableList<String> = ArrayList()

class SlotsAdapter(private val activity: Activity) :
    RecyclerView.Adapter<SlotsAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: SlotsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            SlotsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = slotItemList.size

    fun setAllItems(
        newListOfSlots: MutableList<String>,
        newBookedListOfSlots: MutableList<String>
    ) {
        slotItemList.clear()
        slotItemList.addAll(newListOfSlots)
        bookedSlotItemList.clear()
        bookedSlotItemList.addAll(newBookedListOfSlots)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = slotItemList[position]

        holder.binding.apply {
            timeSlotTextView.text = item
            this.rootLayout.background = ResourcesCompat.getDrawable(
                activity.resources,
                R.drawable.available_slot,
                null
            )
            if (bookedSlotItemList.contains(item)) {
                this.rootLayout.background = ResourcesCompat.getDrawable(
                    activity.resources,
                    R.drawable.unavailable_slot,
                    null
                )
            }
        }
    }

}