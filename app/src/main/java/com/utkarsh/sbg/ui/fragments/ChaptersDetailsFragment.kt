package com.utkarsh.sbg.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.farmit.utils.extention.handleListApiView
import com.farmit.utils.extention.observeNotNull
import com.farmit.utils.extention.showSnackBar
import com.google.android.material.snackbar.Snackbar
import com.utkarsh.sbg.R
import com.utkarsh.sbg.common.EXTRA_CHAPTER_DETAILS
import com.utkarsh.sbg.common.base.BaseFragment
import com.utkarsh.sbg.data.models.ChaptersModelItem
import com.utkarsh.sbg.databinding.FragmentChaptersDetailsBinding
import com.utkarsh.sbg.ui.adapters.ChaptersAdapter
import com.utkarsh.sbg.ui.adapters.VersesAdapter
import com.utkarsh.sbg.ui.viewmodels.InfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChaptersDetailsFragment : BaseFragment<FragmentChaptersDetailsBinding>() {

    private val infoViewModel: InfoViewModel by viewModels()
    private lateinit var verseAdapter: VersesAdapter
    private var chapterDetails: ChaptersModelItem? = null

    override fun onViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChaptersDetailsBinding {
        return FragmentChaptersDetailsBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        chapterDetails = requireArguments().getParcelable(EXTRA_CHAPTER_DETAILS)
        chapterDetails?.chapterNumber?.let { infoViewModel.getVersesList(it) }
        setData()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        verseAdapter = VersesAdapter()
        binding.recyclerViewVerses.adapter = verseAdapter
    }

    private fun setData() {
        binding.toolbar.toolbarTitle.text = String.format("%s %s", "Chapter", chapterDetails?.chapterNumber.toString())
        binding.tvChapterNumber.text = String.format("||%s %s||", "Chapter", chapterDetails?.chapterNumber.toString())
        binding.tvChapterVerses.text = String.format("||%s %s||", chapterDetails?.versesCount.toString(), "Verses")
        binding.tvChapterNameInEnglish.text = chapterDetails?.nameTranslated
        binding.tvChapterMeaningInEnglish.text = chapterDetails?.nameMeaning
        binding.tvChapterSummaryInEnglish.text = chapterDetails?.chapterSummary
    }

    override fun initListener() {

    }

    override fun initObserver() {
        observeNotNull(infoViewModel.versesListLiveData) {
            it.handleListApiView(
                binding.progressLayout,
                skipIds = arrayListOf(R.id.toolbar)
            ) { result ->
                result.let { versesList ->
                    if (versesList != null) {
                        verseAdapter.addAll(versesList)
                    }
                }
            }
        }
    }
}