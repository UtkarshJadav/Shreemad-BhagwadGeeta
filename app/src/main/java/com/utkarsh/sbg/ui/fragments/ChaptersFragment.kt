package com.utkarsh.sbg.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.farmit.utils.extention.handleListApiView
import com.farmit.utils.extention.observeNotNull
import com.farmit.utils.listener.OnSingleClickListener
import com.utkarsh.sbg.R
import com.utkarsh.sbg.common.base.BaseFragment
import com.utkarsh.sbg.databinding.FragmentChaptersBinding
import com.utkarsh.sbg.ui.adapters.ChaptersAdapter
import com.utkarsh.sbg.ui.viewmodels.InfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChaptersFragment : BaseFragment<FragmentChaptersBinding>() {

    private lateinit var chapAdapter: ChaptersAdapter
    private val infoViewModel: InfoViewModel by viewModels()

    override fun onViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentChaptersBinding {
        return FragmentChaptersBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        infoViewModel.getChaptersList()
        binding.toolbar.toolbarTitle.text = getString(R.string.chapters)
        binding.toolbar.ivBack.isVisible = false
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        chapAdapter = ChaptersAdapter()
        binding.recyclerViewChapters.adapter = chapAdapter
    }

    override fun initListener() {

    }



    override fun initObserver() {
        observeNotNull(infoViewModel.chaptersListLiveData) {
            it.handleListApiView(
                binding.progressLayout,
                skipIds = arrayListOf(R.id.toolbar)
            ) { result ->
                result.let { chaptersList ->
                    if (chaptersList != null) {
                        chapAdapter.addAll(chaptersList)
                    }
                }
            }
        }
    }
}