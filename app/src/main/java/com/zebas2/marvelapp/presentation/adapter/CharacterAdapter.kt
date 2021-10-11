package com.zebas2.marvelapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zebas2.marvelapp.R
import com.zebas2.marvelapp.data.model.Character
import com.zebas2.marvelapp.data.util.Constants.IMAGE_PORTRAIT_FANTASTIC
import com.zebas2.marvelapp.databinding.CharacterItemBinding

private const val TAG = "CharacterAdapter"

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, callback)

    fun submitList(list: List<Character>) {
        differ.submitList(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = differ.currentList[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Character) -> Unit)? = null

    fun setOnItemCLickListener(listener: (Character) -> Unit) {
        onItemClickListener = listener
    }

    inner class MyViewHolder(private val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.txvTitleCardView.text = character.name

            Glide.with(binding.imgCardView.context)
                .load(character.thumbnail?.getCompletePath(IMAGE_PORTRAIT_FANTASTIC))
                .error(R.drawable.ic_wifi_off)
                .into(binding.imgCardView)

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(character)
                }
            }
        }

    }

}