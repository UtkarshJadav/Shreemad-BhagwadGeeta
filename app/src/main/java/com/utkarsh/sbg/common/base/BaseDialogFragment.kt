package com.farmit.ui.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.utkarsh.sbg.utils.extention.hideKeyboard

abstract class BaseDialogFragment<B : ViewBinding> : DialogFragment(), View.OnClickListener {
    private var _binding: B? = null
    protected val binding get() = _binding
    abstract fun onViewBinding(inflater: LayoutInflater, container: ViewGroup?): B
    abstract fun initView()
    abstract fun initListener()
    abstract fun initObserver()

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = onViewBinding(inflater, container)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    override fun onClick(view: View?) {
        hideKeyboard()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}