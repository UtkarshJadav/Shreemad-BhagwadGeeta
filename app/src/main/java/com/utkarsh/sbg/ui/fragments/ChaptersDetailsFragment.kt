package com.utkarsh.sbg.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.farmit.utils.extention.showSnackBar
import com.google.android.material.snackbar.Snackbar
import com.utkarsh.sbg.common.EXTRA_CHAPTER_DETAILS
import com.utkarsh.sbg.common.base.BaseFragment
import com.utkarsh.sbg.data.models.ChaptersModelItem
import com.utkarsh.sbg.databinding.FragmentChaptersDetailsBinding
import com.utkarsh.sbg.ui.adapters.ChaptersAdapter
import com.utkarsh.sbg.ui.viewmodels.InfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChaptersDetailsFragment : BaseFragment<FragmentChaptersDetailsBinding>() {

    private val infoViewModel: InfoViewModel by viewModels()
    private var chapterDetails: ChaptersModelItem? = null

    override fun onViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChaptersDetailsBinding {
        return FragmentChaptersDetailsBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        chapterDetails = requireArguments().getParcelable(EXTRA_CHAPTER_DETAILS)
        setData()
    }

    private fun setData() {
        binding.toolbar.toolbarTitle.text = String.format("%s %s", "Chapter", chapterDetails?.chapterNumber.toString())
        binding.tvChapterNumber.text = String.format("||%s %s||", "Chapter", chapterDetails?.chapterNumber.toString())
        binding.tvChapterNameInEnglish.text = chapterDetails?.translation
        binding.tvChapterMeaningInEnglish.text = chapterDetails?.meaning?.en
        binding.tvChapterSummaryInEnglish.text = chapterDetails?.summary?.en
        binding.tvChapterNameInHindi.text = chapterDetails?.name
        binding.tvChapterMeaningInHindi.text = chapterDetails?.meaning?.hi
        binding.tvChapterSummaryInHindi.text = chapterDetails?.summary?.hi
    }

    override fun initListener() {

    }

    override fun initObserver() {

    }
}