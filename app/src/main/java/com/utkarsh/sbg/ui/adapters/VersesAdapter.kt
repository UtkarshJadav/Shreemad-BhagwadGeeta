package com.utkarsh.sbg.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farmit.utils.extention.inflate
import com.farmit.utils.listener.OnSingleClickListener
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
    private val versesList: ArrayList<VersesListModelItem?> = arrayListOf(),
    private val singleClickListener: OnSingleClickListener
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
            binding.cvRootVerse.setOnClickListener(singleClickListener)
        }

        fun bind(item: VersesListModelItem) = with(item) {
            binding.tvSlug.text = String.format("||%s||", slug)
            binding.tvVerseInSanskrit.text = text?.replace("\n\n", "\n")
            binding.tvVerseInEnglish.text = transliteration
            binding.cvRootVerse.tag = bindingAdapterPosition
        }
    }
}