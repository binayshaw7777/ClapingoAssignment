package com.binayshaw7777.clapingoassignemnt.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binayshaw7777.clapingoassignemnt.databinding.SlotsItemLayoutBinding

private var slotItemList: MutableList<String> = ArrayList()
private var bookedSlotItemList: MutableList<String> = ArrayList()

class SlotsAdapter(activity: Activity) : RecyclerView.Adapter<SlotsAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: SlotsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            SlotsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = slotItemList.size

    fun setAllItems(newListOfSlots: MutableList<String>, newBookedListOfSlots: MutableList<String>) {
        slotItemList.clear()
        slotItemList.addAll(newListOfSlots)
        bookedSlotItemList.clear()
        bookedSlotItemList.addAll(bookedSlotItemList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = slotItemList[position]

        holder.binding.apply {
            timeSlotTextView.text = item
        }
    }

}