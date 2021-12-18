package com.utkarsh.sbg.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farmit.utils.extention.inflate
import com.utkarsh.sbg.R
import com.utkarsh.sbg.common.EXTRA_CHAPTER_DETAILS
import com.utkarsh.sbg.common.base.BaseAdapter
import com.utkarsh.sbg.data.models.ChaptersModelItem
import com.utkarsh.sbg.databinding.ListItemChapterBinding
import com.utkarsh.sbg.utils.extention.navigate


class ChaptersAdapter(
    private val chaptersList: ArrayList<ChaptersModelItem?> = arrayListOf()
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

    inner class ViewHolder(private val binding: ListItemChapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                it.navigate(R.id.action_chaptersFragment_to_chaptersDetailsFragment){
                    putParcelable(EXTRA_CHAPTER_DETAILS, chaptersList[bindingAdapterPosition])
                }
            }
        }

        fun bind(item: ChaptersModelItem) = with(item) {
            binding.tvChapterNumber.text = String.format("%s %s", "Chapter", chapterNumber.toString())
            binding.tvChapterName.text = translation
            binding.tvChapterNameInHindi.text = name
            binding.tvShloksInChapter.text = String.format("%s %s", versesCount.toString(), "Verses")
            binding.tvChapterMeaning.text = meaning?.en
            binding.tvChapterMeaningInHindi.text = meaning?.hi
            binding.tvChapterSummary.text = summary?.en
        }
    }
}