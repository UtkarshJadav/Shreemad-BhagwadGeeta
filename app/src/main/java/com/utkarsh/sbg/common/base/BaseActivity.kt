package com.farmit.ui.common.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.review.ReviewManagerFactory
import com.utkarsh.sbg.data.local.pref.Preference
import com.utkarsh.sbg.common.MY_UPDATE_REQUEST_CODE
import com.farmit.utils.common.ImagePickerUtils
import com.utkarsh.sbg.utils.extention.hideKeyboard
import javax.inject.Inject

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var preference: Preference
    protected lateinit var binding: B
    abstract fun onViewBinding(): B
    abstract fun initView()
    abstract fun initListener()
    abstract fun initObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = onViewBinding()
        setContentView(binding.root)
        initView()
        initListener()
        initObserver()
    }

    override fun onClick(view: View?) {
        hideKeyboard(view)
    }

    /* Codes for checking the application new update is available in play store,
    If yes, then force update application. */
    var appUpdateManager: AppUpdateManager? = null
    fun appInAppUpdateCheck() {
        if (appUpdateManager == null) {
            appUpdateManager = AppUpdateManagerFactory.create(this@BaseActivity)
        }
        if (appUpdateManager != null) {
            appUpdateManager?.registerListener(object : InstallStateUpdatedListener {
                override fun onStateUpdate(state: InstallState) {
                    try {
                        if (state.installStatus() == InstallStatus.DOWNLOADED) {
                            appUpdateManager?.completeUpdate()
                        } else if (state.installStatus() == InstallStatus.INSTALLED) {
                            if (appUpdateManager != null) {
                                try {
                                    appUpdateManager?.unregisterListener(this)
                                } catch (ignore: java.lang.Exception) {
                                }
                            }
                        }
                    } catch (ignore: Exception) {
                    }
                }
            })
            appUpdateManager?.appUpdateInfo
                ?.addOnSuccessListener {
                    if (it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && it.isUpdateTypeAllowed(
                            AppUpdateType.IMMEDIATE
                        )
                    ) {
                        // If an in-app update is already running, resume the update.
                        appUpdateManager?.startUpdateFlowForResult(
                            it,
                            AppUpdateType.IMMEDIATE,
                            this,
                            MY_UPDATE_REQUEST_CODE
                        )
                    } else if (it.installStatus() == InstallStatus.DOWNLOADED) {
                        appUpdateManager?.completeUpdate()
                    }
                }
        }
    }

    /* Codes for popup the dialog for asking to user for give a rating for app in play store */
    fun appInAppReviewForRating(callback: (isSuccess: Boolean) -> Unit) {
        val manager = ReviewManagerFactory.create(this@BaseActivity)
        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { request ->
            callback.invoke(request.isSuccessful)
            if (request.isSuccessful) {
                val reviewInfo = request.result
                manager.launchReviewFlow(this@BaseActivity, reviewInfo).addOnFailureListener {
                    callback.invoke(false)
                }
            }
        }
        request.addOnFailureListener {
            callback.invoke(false)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        ImagePickerUtils.onActivityResult(requestCode, resultCode, data)
    }
}