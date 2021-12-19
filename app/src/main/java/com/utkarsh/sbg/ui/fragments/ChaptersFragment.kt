package com.utkarsh.sbg.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.farmit.utils.extention.handleListApiView
import com.farmit.utils.extention.observeNotNull
import com.farmit.utils.listener.OnSingleClickListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.utkarsh.sbg.R
import com.utkarsh.sbg.common.EXTRA_CHAPTER_DETAILS
import com.utkarsh.sbg.common.base.BaseFragment
import com.utkarsh.sbg.databinding.FragmentChaptersBinding
import com.utkarsh.sbg.ui.adapters.ChaptersAdapter
import com.utkarsh.sbg.ui.viewmodels.InfoViewModel
import com.utkarsh.sbg.utils.extention.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChaptersFragment : BaseFragment<FragmentChaptersBinding>() {

    private lateinit var chapAdapter: ChaptersAdapter
    private val infoViewModel: InfoViewModel by viewModels()
    private var mInterstitialAd: InterstitialAd? = null
    private var adRequest = AdRequest.Builder().build()

    override fun onViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChaptersBinding {
        return FragmentChaptersBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        infoViewModel.getChaptersList()
        binding.toolbar.toolbarTitle.text = getString(R.string.chapters)
        binding.toolbar.ivBack.isVisible = false
        setupRecyclerView()

        InterstitialAd.load(
            requireContext(),
            getString(R.string.interstitial_id),
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                }
            })
    }

    private fun setupRecyclerView() {
        chapAdapter = ChaptersAdapter(singleClickListener = singleClickListener)
        binding.recyclerViewChapters.adapter = chapAdapter
    }

    override fun initListener() {

    }

    private val singleClickListener = object : OnSingleClickListener() {
        override fun onSingleClick(view: View) {
            when (view.id) {
                R.id.cvRootChapter -> {
                    if (mInterstitialAd != null) {
                        mInterstitialAd?.show(requireActivity())
                    }
                    val data = chapAdapter.getSingleItem(view.tag as Int)
                    navigate(R.id.action_chaptersFragment_to_chaptersDetailsFragment) {
                        putParcelable(EXTRA_CHAPTER_DETAILS, data)
                    }
                }
            }
        }
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