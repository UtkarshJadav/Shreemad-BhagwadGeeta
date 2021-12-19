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
import com.utkarsh.sbg.common.EXTRA_VERSE_DETAILS
import com.utkarsh.sbg.common.base.BaseFragment
import com.utkarsh.sbg.data.models.ChaptersModelItem
import com.utkarsh.sbg.databinding.FragmentChaptersDetailsBinding
import com.utkarsh.sbg.ui.adapters.VersesAdapter
import com.utkarsh.sbg.ui.viewmodels.InfoViewModel
import com.utkarsh.sbg.utils.extention.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChaptersDetailsFragment : BaseFragment<FragmentChaptersDetailsBinding>() {

    private val infoViewModel: InfoViewModel by viewModels()
    private lateinit var verseAdapter: VersesAdapter
    private var chapterDetails: ChaptersModelItem? = null
    private var mInterstitialAd: InterstitialAd? = null
    private var adRequest = AdRequest.Builder().build()

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

        InterstitialAd.load(requireContext(),"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        })
    }

    private fun setupRecyclerView() {
        verseAdapter = VersesAdapter(singleClickListener = singleClickListener)
        binding.recyclerViewVerses.adapter = verseAdapter
    }

    private fun setData() {
        binding.toolbar.toolbarTitle.text = String.format("%s %s", "Chapter", chapterDetails?.chapterNumber.toString())
        binding.tvChapterNumber.text = String.format("||%s %s||", "Chapter", chapterDetails?.chapterNumber.toString())
        binding.tvChapterVerses.text = String.format("||%s %s||", chapterDetails?.versesCount.toString(), "Verses")
        binding.tvChapterNameInEnglish.text = chapterDetails?.nameTranslated
        binding.tvChapterMeaningInEnglish.text = chapterDetails?.nameMeaning
        binding.tvChapterSummaryInEnglish.text = chapterDetails?.chapterSummary?.replace("\n\n", "\n")
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

                R.id.cvRootVerse -> {
                    if (mInterstitialAd != null) {
                        mInterstitialAd?.show(requireActivity())
                    }
                    val data = verseAdapter.getSingleItem(view.tag as Int)
                    navigate(R.id.action_chaptersDetailsFragment_to_verseDetailsFragment) {
                        putParcelable(EXTRA_VERSE_DETAILS, data)
                    }
                }
            }
        }
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