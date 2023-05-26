package com.orra.pet.base

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.orra.pet.R
import com.orra.pet.base.views.ErrorView
import com.orra.pet.base.views.InfoView
import com.orra.pet.base.views.LoadingView
import com.orra.pet.utils.className
import com.orra.pet.utils.observeLD
import com.orra.pet.utils.setThemedContent

abstract class BaseFragment : Fragment(R.layout.fragment_base) {

    protected val rootComposeView get() = contentView!!
    private var contentView: ComposeView? = null
    private var onRetryConsumers = mutableListOf<(ErrorAction) -> Unit>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.contentView).setThemedContent { FragmentContent() }
    }

    protected open fun <E : Any> observeBase(viewModel: BaseViewModel<E>) {
        observeLD(viewModel.modalLoadingState) {
            view?.findViewById<ComposeView>(R.id.loadingView)?.setThemedContent { LoadingView(it) }
        }
        observeLD(viewModel.errorState) {
            view?.findViewById<ComposeView>(R.id.errorView)?.setThemedContent {
                ErrorView(errorActions = it, onRetryClicked = viewModel::onRetryActions)
            }
        }
        observeLD(viewModel.notificationsEvents) {
            view?.findViewById<ComposeView>(R.id.infoView)?.setThemedContent { InfoView(it) }
        }
        onRetryConsumers.add(viewModel::onRetryAction)
    }

    @Composable
    open fun FragmentContent() {

    }

    protected fun DialogFragment.showDialog() {
        val manager = this@BaseFragment.requireActivity().supportFragmentManager
        manager.fragments.lastOrNull()?.let {
            if (it::class.java == this::class.java) {
                return
            }
        }
        show(manager, className())
    }

    override fun onDestroyView() {
        contentView = null
        super.onDestroyView()
    }

    protected fun onBack() {
        activity?.onBackPressed()
    }

}