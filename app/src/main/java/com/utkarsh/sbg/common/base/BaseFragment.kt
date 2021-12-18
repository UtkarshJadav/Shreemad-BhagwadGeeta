package com.utkarsh.sbg.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.utkarsh.sbg.data.local.pref.Preference
import com.farmit.utils.common.Unauthorized
import com.utkarsh.sbg.utils.extention.hideKeyboard
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

abstract class BaseFragment<B : ViewBinding> : Fragment(), View.OnClickListener {

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
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        initView()
        initListener()
        initObserver()
    }

    override fun onClick(view: View?) {
        hideKeyboard(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe
    fun onEvent(unauthorizedAccess: Unauthorized) {
       //Perform action
    }
}