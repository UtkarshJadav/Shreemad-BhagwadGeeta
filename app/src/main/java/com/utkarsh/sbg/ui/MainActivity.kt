package com.utkarsh.sbg.ui

import com.farmit.ui.common.base.BaseActivity
import com.utkarsh.sbg.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() = Unit

    override fun initListener() = Unit

    override fun initObserver() = Unit

    override fun onResume() {
        super.onResume()
        appInAppUpdateCheck()
    }
}