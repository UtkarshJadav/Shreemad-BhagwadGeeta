package com.utkarsh.sbg.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farmit.utils.extention.inflate
import com.utkarsh.sbg.R
import com.utkarsh.sbg.common.EXTRA_CHAPTER_DETAILS
import com.utkarsh.sbg.common.EXTRA_VERSE_DETAILS
import com.utkarsh.sbg.common.base.BaseAdapter
import com.utkarsh.sbg.data.models.ChaptersModelItem
import com.utkarsh.sbg.data.models.VersesListModelItem
import com.utkarsh.sbg.databinding.ListItemChapterBinding
import com.utkarsh.sbg.databinding.ListItemVerseBinding
import com.utkarsh.sbg.utils.extention.navigate


class VersesAdapter(
    private val versesList: ArrayList<VersesListModelItem?> = arrayListOf()
) : BaseAdapter<VersesListModelItem?>(versesList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ListItemVerseBinding.bind(parent.inflate(R.layout.list_item_verse)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        when (holder) {
            is ViewHolder -> {
                versesList[position]?.let { holder.bind(it) }
            }
        }
    }

    inner class ViewHolder(private val binding: ListItemVerseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                it.navigate(R.id.action_chaptersDetailsFragment_to_verseDetailsFragment){
                    putParcelable(EXTRA_VERSE_DETAILS, versesList[bindingAdapterPosition])
                }
            }
        }

        fun bind(item: VersesListModelItem) = with(item) {
            binding.tvSlug.text = slug
            binding.tvVerseInSanskrit.text = text?.replace("\n\n", "\n")
            binding.tvVerseInEnglish.text = transliteration
        }
    }
}