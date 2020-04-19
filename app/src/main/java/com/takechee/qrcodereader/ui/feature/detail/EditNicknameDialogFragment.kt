package com.takechee.qrcodereader.ui.feature.detail

import android.app.Dialog
import android.content.DialogInterface
import android.content.DialogInterface.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.common.base.BaseDialogFragment
import com.takechee.qrcodereader.util.extension.parentViewModels
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditNicknameDialogFragment : BaseDialogFragment() {

    companion object {
        private const val TAG = "EditNicknameDialogFragment"
        private const val PARAM_NICKNAME = "NICKNAME"
        private const val PARAM_IS_WEB_URL = "IS_WEB_URL"

        fun show(fragment: Fragment, nickname: String, isWebUrl: Boolean) {
            val dialog = EditNicknameDialogFragment().apply {
                arguments = bundleOf(
                    PARAM_NICKNAME to nickname,
                    PARAM_IS_WEB_URL to isWebUrl
                )
            }
            dialog.show(fragment.childFragmentManager, TAG)
        }
    }

    @Inject
    lateinit var inputMethodManager: InputMethodManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: DetailViewModel by parentViewModels { viewModelFactory }

    private val initNickname: String by lazy {
        requireArguments().getString(PARAM_NICKNAME, "")
    }

    private val isWebUrl: Boolean by lazy {
        requireArguments().getBoolean(PARAM_IS_WEB_URL)
    }


    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.edit_nickname)
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_detail_edit_nickname_content, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText = view.findViewById<EditText>(R.id.edit_text).also { editText ->
            editText.setText(initNickname, TextView.BufferType.NORMAL)
        }

        if (isWebUrl) view.findViewById<Button>(R.id.get_by_url_button).also { button ->
            button.isVisible = true
            button.setOnClickListener {
                viewModel.onGetTitleByUrlClick().receiveEvent(viewLifecycleOwner) { title ->
                    editText.setText(title, TextView.BufferType.NORMAL)
                }
            }
        }

        (dialog as? AlertDialog)?.let { dialog ->
            val verticalSpace = resources.getDimensionPixelSize(R.dimen.margin_small)
            val horizontalSpace = resources.getDimensionPixelSize(R.dimen.margin_normal)
            dialog.setView(view, horizontalSpace, verticalSpace, horizontalSpace, verticalSpace)
            dialog.setButton(BUTTON_POSITIVE, resources.getString(R.string.save)) { _, _ ->
                val editValue = view.findViewById<EditText>(R.id.edit_text).text.toString()
                if (editValue.isEmpty()) return@setButton
                if (editValue == initNickname) return@setButton
                viewModel.onEditNicknamePositiveClick(editValue)
            }
        }
    }
}