package com.takechee.qrcodereader.ui.feature.detail

import android.graphics.Bitmap
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ItemDetailViewContentQrimageBinding
import com.takechee.qrcodereader.databinding.ItemDetailViewContentTextBinding
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
}