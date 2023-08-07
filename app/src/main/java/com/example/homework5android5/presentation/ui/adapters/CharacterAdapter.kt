package com.example.homework5android5.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework5android5.databinding.ItemBinding
import com.example.homework5android5.domain.models.CharacterModel

class CharacterAdapter(
    private val onClick: (character: CharacterModel) -> Unit,
    private val deleteItem: (character:CharacterModel) -> Unit
) :
    ListAdapter<CharacterModel, CharacterAdapter.CharacterViewHolder>(DiffUtilCallback()) {

    inner class CharacterViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(character: CharacterModel) {
            Glide.with(binding.ivCharacterImage).load(character.image)
                .into(binding.ivCharacterImage)
            binding.tvName.text = character.name
        }

        init {
            itemView.setOnClickListener {
                onClick(getItem(bindingAdapterPosition))
            }
            binding.btnDeleteView.setOnClickListener {
                deleteItem(getItem(bindingAdapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {

        class DiffUtilCallback : DiffUtil.ItemCallback<CharacterModel>() {
            override fun areItemsTheSame(
                oldItem: CharacterModel,
                newItem: CharacterModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CharacterModel,
                newItem: CharacterModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}