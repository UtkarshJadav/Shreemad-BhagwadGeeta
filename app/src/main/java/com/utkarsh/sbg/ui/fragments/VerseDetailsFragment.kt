package com.utkarsh.sbg.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.farmit.utils.extention.handleListApiView
import com.farmit.utils.extention.observeNotNull
import com.farmit.utils.extention.showSnackBar
import com.farmit.utils.listener.OnSingleClickListener
import com.google.android.material.snackbar.Snackbar
import com.utkarsh.sbg.R
import com.utkarsh.sbg.common.EXTRA_CHAPTER_DETAILS
import com.utkarsh.sbg.common.EXTRA_VERSE_DETAILS
import com.utkarsh.sbg.common.SWAMI_SIVANANDA
import com.utkarsh.sbg.common.SWAMI_TEJOMAYANANDA
import com.utkarsh.sbg.common.base.BaseFragment
import com.utkarsh.sbg.data.models.ChaptersModelItem
import com.utkarsh.sbg.data.models.VersesListModelItem
import com.utkarsh.sbg.databinding.FragmentChaptersDetailsBinding
import com.utkarsh.sbg.databinding.FragmentVerseDetailsBinding
import com.utkarsh.sbg.ui.adapters.ChaptersAdapter
import com.utkarsh.sbg.ui.adapters.VersesAdapter
import com.utkarsh.sbg.ui.viewmodels.InfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerseDetailsFragment : BaseFragment<FragmentVerseDetailsBinding>() {

    private var verseDetailsModel: VersesListModelItem? = null

    override fun onViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentVerseDetailsBinding {
        return FragmentVerseDetailsBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        verseDetailsModel = requireArguments().getParcelable(EXTRA_VERSE_DETAILS)
        setData()
    }

    override fun initListener() {
        binding.toolbar.ivBack.setOnClickListener(singleClickListener)
    }

    private val singleClickListener = object : OnSingleClickListener() {
        override fun onSingleClick(view: View) {
            when (view.id) {
                R.id.ivBack -> {
                    requireActivity().onBackPressed()
                }
            }
        }
    }

    private fun setData() {
        binding.toolbar.toolbarTitle.text = verseDetailsModel?.slug
        binding.tvSlug.text = String.format("||%s||", verseDetailsModel?.slug)
        binding.tvVerseText.text = verseDetailsModel?.text?.replace("\n\n", "\n")
        binding.tvVerseTransliteration.text = verseDetailsModel?.transliteration
        binding.tvVerseWordMeanings.text = verseDetailsModel?.wordMeanings?.replace(";",";\n")
        binding.tvVerseEnglishSummary.text = verseDetailsModel?.translations?.find { it?.authorName == SWAMI_SIVANANDA }?.description?.replace("\n\n", "\n")
        binding.tvVerseHindiSummary.text = verseDetailsModel?.translations?.find { it?.authorName == SWAMI_TEJOMAYANANDA }?.description?.replace("\n\n", "\n")
    }

    override fun initObserver() {

    }
}