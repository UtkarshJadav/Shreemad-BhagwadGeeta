package com.utkarsh.sbg.ui

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.farmit.ui.common.base.BaseActivity
import com.utkarsh.sbg.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity :  BaseActivity<ActivitySplashBinding>() {

    private val handler = Looper.myLooper()?.let { Handler(it) }

    override fun onViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }
    private val runnable = Runnable {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

    override fun initView() = Unit

    override fun initListener() = Unit

    override fun initObserver() = Unit

    override fun onResume() {
        super.onResume()
        handler?.postDelayed(runnable, 2000)
    }

    override fun onPause() {
        super.onPause()
        handler?.removeCallbacks(runnable)
    }
}