package com.example.mynotapplication.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.PrecomputedTextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotapplication.database.EntitiyNote
import com.example.mynotapplication.databinding.PinnedRvItemsBinding
import com.example.mynotapplication.generated.callback.OnClickListener
import com.example.mynotapplication.utils.ItemClickListener

class PinnedRvAdapter(private var pinnedNoteList: ArrayList<EntitiyNote>,private val listener: ItemClickListener) :
    RecyclerView.Adapter<PinnedRvAdapter.CustomViewHolder>() {

    class CustomViewHolder(val binding: PinnedRvItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(entitiyNote: EntitiyNote, listener: ItemClickListener) {
            binding.pinnedtitle.setTextFuture(
                PrecomputedTextCompat.getTextFuture(
                    entitiyNote.notes.title, binding.pinnedtitle.textMetricsParamsCompat, null
                )
            )

            binding.pinneddescription.text = entitiyNote.notes.description
            binding.pinnedcardview.setCardBackgroundColor(Color.parseColor(entitiyNote.notes.color))
            binding.pinnedcardview.setOnClickListener {
                listener.itemClick(entitiyNote)
            }
            binding.imageFilterButton.setOnClickListener {
                listener.deleteItem(binding.imageFilterButton,entitiyNote)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding: PinnedRvItemsBinding = PinnedRvItemsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(pinnedNoteList[position],listener)
    }

    override fun getItemCount(): Int {
        return pinnedNoteList.size
    }
}