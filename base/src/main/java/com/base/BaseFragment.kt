package com.base

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.annotation.IntegerRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.base.listeners.ClickListener

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment(),
    ViewTreeObserver.OnGlobalLayoutListener, BaseNavigator {

    var binding: T? = null

    abstract val viewModel: V

    private var rootView: View? = null

    private var loading: AlertDialog? = null

    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    @IntegerRes
    protected abstract fun getVariable(): Int

    protected abstract fun onInitialize(savedInstanceState: Bundle?)

    protected abstract fun onTerminate()

    protected abstract fun onShow()

    protected abstract fun onHide()

    override fun onGlobalLayout() {
        rootView!!.viewTreeObserver.removeOnGlobalLayoutListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        rootView = binding?.root
        rootView!!.viewTreeObserver.addOnGlobalLayoutListener(this)

        binding?.executePendingBindings()

        binding?.setVariable(getVariable(), viewModel)

        initDialog()

        onInitialize(savedInstanceState)

        return rootView
    }

    private fun initDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())

        builder.setCancelable(false)
        builder.setView(R.layout.layout_loading_dialog_default)
        loading = builder.create()
        loading!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDetach() {
        onTerminate()
        super.onDetach()
    }

    override fun onStart() {
        super.onStart()
        onShow()
    }

    override fun onStop() {
        super.onStop()
        onHide()
    }

    override fun getActivityB(): Activity? {
        return activity
    }

    override fun string(@StringRes resId: Int): String {
        return when {
            resId != 0 -> getString(resId)
            else -> ""
        }
    }

    override fun showLoadingDialog() {
        if (loading != null && !loading!!.isShowing) {
            loading!!.show()
        }
    }

    override fun hideLoadingDialog() {
        if (loading != null && loading!!.isShowing) {
            loading!!.dismiss()
        }
    }

    override fun showInternetConnectionDialog(listener: ClickListener?) {
        showConfirmationDialog(
            title = R.string.title_internet,
            message = R.string.error_message_network_not_available,
            negative = R.string.button_ok,
            listener = listener
        )
    }

    override fun showConfirmationDialog(
        title: String,
        message: String,
        positive: String?,
        negative: String?,
        listener: ClickListener?
    ) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(false)

        positive?.let {
            builder.setPositiveButton(it) { dialog, id ->
                listener?.onClick(null, id)
                dialog.cancel()
            }
        }

        negative?.let {
            builder.setPositiveButton(it) { dialog, id ->
                listener?.onClick(null, id)
                dialog.cancel()
            }
        }

        builder.create().show()
    }

    override fun showConfirmationDialog(
        title: Int,
        message: Int,
        positive: Int,
        negative: Int,
        listener: ClickListener?
    ) {
        showConfirmationDialog(
            string(title),
            string(message),
            string(positive),
            string(negative),
            listener
        )
    }
}