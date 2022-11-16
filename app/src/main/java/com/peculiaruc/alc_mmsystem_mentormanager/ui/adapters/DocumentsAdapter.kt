package com.peculiaruc.alc_mmsystem_mentormanager.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peculiaruc.alc_mmsystem_mentormanager.data.models.DocumentDto
import com.peculiaruc.alc_mmsystem_mentormanager.databinding.ItemDocumentBinding

class DocumentsAdapter (
    private val documentClickListener : (DocumentDto) -> Unit,
) :
    ListAdapter<DocumentDto, DocumentsAdapter.ViewHolder>(TrainingDiffCallback()) {

    class ViewHolder(val binding: ItemDocumentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item : DocumentDto,
            documentClickListener : (DocumentDto) -> Unit,
        )
        {
            binding.textViewDocumentName.text = item.name
            binding.root.setOnClickListener {
                documentClickListener.invoke(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemDocumentBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position),documentClickListener)

}

class TrainingDiffCallback : DiffUtil.ItemCallback<DocumentDto>() {
    override fun areItemsTheSame(oldItem: DocumentDto, newItem: DocumentDto): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DocumentDto, newItem: DocumentDto): Boolean {
        return oldItem.id == newItem.id
    }

}