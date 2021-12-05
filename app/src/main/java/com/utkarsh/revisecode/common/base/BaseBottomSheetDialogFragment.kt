package com.utkarsh.revisecode.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.utkarsh.revisecode.data.local.pref.Preference
import com.utkarsh.revisecode.R
import javax.inject.Inject

abstract class BaseBottomSheetDialogFragment<B : ViewBinding?> : BottomSheetDialogFragment(), View.OnClickListener {

    @Inject
    lateinit var preference: Preference
    private var _binding: B? = null
    protected val binding get() = _binding!!
    abstract fun onViewBinding(inflater: LayoutInflater, container: ViewGroup?): B
    abstract fun initView()
    abstract fun initListener()
    abstract fun initObserver()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = onViewBinding(inflater, container)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onClick(view: View?) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}