package com.utkarsh.sbg.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farmit.utils.extention.inflate
import com.farmit.utils.listener.OnSingleClickListener
import com.utkarsh.sbg.R
import com.utkarsh.sbg.common.EXTRA_CHAPTER_DETAILS
import com.utkarsh.sbg.common.base.BaseAdapter
import com.utkarsh.sbg.data.models.ChaptersModelItem
import com.utkarsh.sbg.databinding.ListItemChapterBinding


class ChaptersAdapter(
    private val chaptersList: ArrayList<ChaptersModelItem?> = arrayListOf(),
    private val singleClickListener: OnSingleClickListener
) : BaseAdapter<ChaptersModelItem?>(chaptersList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ListItemChapterBinding.bind(parent.inflate(R.layout.list_item_chapter)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        when (holder) {
            is ViewHolder -> {
                chaptersList[position]?.let { holder.bind(it) }
            }
        }
    }

    inner class ViewHolder(val binding: ListItemChapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.cvRootChapter.setOnClickListener(singleClickListener)
        }

        fun bind(item: ChaptersModelItem) = with(item) {
            binding.tvChapterNumber.text =
                String.format("||%s %s||", "Chapter", chapterNumber.toString())
            binding.tvChapterName.text = nameTranslated
            binding.tvChapterNameInHindi.text = name
            binding.tvShloksInChapter.text =
                String.format("||%s %s||", versesCount.toString(), "Verses")
            binding.tvChapterMeaning.text = nameMeaning
            binding.tvChapterMeaningInHindi.text = nameTransliterated
            binding.tvChapterSummary.text = chapterSummary
            binding.cvRootChapter.tag = bindingAdapterPosition
        }
    }
}