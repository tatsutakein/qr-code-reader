package com.takechee.qrcodereader.ui.feature.detail

import android.graphics.Bitmap
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.*
import com.xwray.groupie.databinding.BindableItem

sealed class DetailViewContent {
    data class QRImage(
        val bitmap: Bitmap
    ) : BindableItem<ItemDetailViewContentQrimageBinding>(1) {
        override fun getLayout(): Int = R.layout.item_detail_view_content_qrimage
        override fun bind(viewBinding: ItemDetailViewContentQrimageBinding, position: Int) {
            viewBinding.qrCodeImageView.setImageBitmap(bitmap)
        }
    }

    data class Title(
        val text: String
    ) : BindableItem<ItemDetailViewContentTextBinding>(2) {
        override fun getLayout(): Int = R.layout.item_detail_view_content_text
        override fun bind(viewBinding: ItemDetailViewContentTextBinding, position: Int) {
            viewBinding.text = text
        }
    }

    data class Text(
        val text: String
    ) : BindableItem<ItemDetailViewContentTextBinding>(3) {
        override fun getLayout(): Int = R.layout.item_detail_view_content_text
        override fun bind(viewBinding: ItemDetailViewContentTextBinding, position: Int) {
            viewBinding.text = text
        }
    }

    data class Button(
        val clickListener: OnClickListener
    ) : BindableItem<ItemDetailViewContentButtonBinding>(4) {
        override fun getLayout(): Int = R.layout.item_detail_view_content_button
        override fun bind(viewBinding: ItemDetailViewContentButtonBinding, position: Int) {
            viewBinding.clickListener = clickListener
        }

        interface OnClickListener {
            fun onButtonClick()
        }
    }

    data class ShareButton(
        val clickListener: OnClickListener
    ) : BindableItem<ItemDetailViewContentShareButtonBinding>(5) {
        override fun getLayout(): Int = R.layout.item_detail_view_content_share_button
        override fun bind(viewBinding: ItemDetailViewContentShareButtonBinding, position: Int) {
            viewBinding.clickListener = clickListener
        }

        interface OnClickListener {
            fun onShareButtonClick()
        }
    }

    sealed class ActionArea {

        interface OnActionClickListener {
            fun onShareActionClick()
            fun onOpenIntentActionClick()
            fun onOpenUrlActionClick()
            fun onCopyToClipBoardActionClick()
        }

        data class UrlAction(
            val clickListener: OnActionClickListener
        ) : BindableItem<ItemDetailViewContentUrlActionBinding>(5) {
            override fun getLayout(): Int = R.layout.item_detail_view_content_url_action
            override fun bind(viewBinding: ItemDetailViewContentUrlActionBinding, position: Int) {
                viewBinding.clickListener = clickListener
            }
        }

        data class TextAction(
            val clickListener: OnActionClickListener
        ) : BindableItem<ItemDetailViewContentTextActionBinding>(5) {
            override fun getLayout(): Int = R.layout.item_detail_view_content_text_action
            override fun bind(viewBinding: ItemDetailViewContentTextActionBinding, position: Int) {
                viewBinding.clickListener = clickListener
            }
        }

        data class SpecifiedAction(
            val clickListener: OnActionClickListener
        ) : BindableItem<ItemDetailViewContentSpecifiedActionBinding>(5) {
            override fun getLayout(): Int = R.layout.item_detail_view_content_specified_action
            override fun bind(
                viewBinding: ItemDetailViewContentSpecifiedActionBinding,
                position: Int
            ) {
                viewBinding.clickListener = clickListener
            }
        }
    }
}