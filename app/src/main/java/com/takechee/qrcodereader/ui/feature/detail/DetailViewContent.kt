package com.takechee.qrcodereader.ui.feature.detail

import android.graphics.Bitmap
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.*
import com.xwray.groupie.databinding.BindableItem

private enum class DetailViewContent {
    QR_IMAGE,
    TITLE,
    TEXT,
    ACTION_AREA;

    val id: Long = (ordinal + 1).toLong()
}

data class DetailViewContentQRImage(
    val bitmap: Bitmap
) : BindableItem<ItemDetailViewContentQrimageBinding>(DetailViewContent.QR_IMAGE.id) {
    override fun getLayout(): Int = R.layout.item_detail_view_content_qrimage
    override fun bind(viewBinding: ItemDetailViewContentQrimageBinding, position: Int) {
        viewBinding.qrCodeImageView.setImageBitmap(bitmap)
    }
}

data class DetailViewContentTitle(
    val text: String
) : BindableItem<ItemDetailViewContentTextBinding>(DetailViewContent.TITLE.id) {
    override fun getLayout(): Int = R.layout.item_detail_view_content_text
    override fun bind(viewBinding: ItemDetailViewContentTextBinding, position: Int) {
        viewBinding.text = text
    }
}

data class DetailViewContentText(
    val text: String
) : BindableItem<ItemDetailViewContentTextBinding>(DetailViewContent.TEXT.id) {
    override fun getLayout(): Int = R.layout.item_detail_view_content_text
    override fun bind(viewBinding: ItemDetailViewContentTextBinding, position: Int) {
        viewBinding.text = text
    }
}

sealed class DetailViewContentActionArea {

    interface OnActionClickListener {
        fun onShareActionClick()
        fun onOpenIntentActionClick()
        fun onOpenUrlActionClick()
        fun onCopyToClipBoardActionClick()
    }

    data class UrlAction(
        val clickListener: OnActionClickListener
    ) : BindableItem<ItemDetailViewContentUrlActionBinding>(DetailViewContent.ACTION_AREA.id) {
        override fun getLayout(): Int = R.layout.item_detail_view_content_url_action
        override fun bind(viewBinding: ItemDetailViewContentUrlActionBinding, position: Int) {
            viewBinding.clickListener = clickListener
        }
    }

    data class TextAction(
        val clickListener: OnActionClickListener
    ) : BindableItem<ItemDetailViewContentTextActionBinding>(DetailViewContent.ACTION_AREA.id) {
        override fun getLayout(): Int = R.layout.item_detail_view_content_text_action
        override fun bind(viewBinding: ItemDetailViewContentTextActionBinding, position: Int) {
            viewBinding.clickListener = clickListener
        }
    }

    data class SpecifiedAction(
        val clickListener: OnActionClickListener
    ) : BindableItem<ItemDetailViewContentSpecifiedActionBinding>(DetailViewContent.ACTION_AREA.id) {
        override fun getLayout(): Int = R.layout.item_detail_view_content_specified_action
        override fun bind(
            viewBinding: ItemDetailViewContentSpecifiedActionBinding,
            position: Int
        ) {
            viewBinding.clickListener = clickListener
        }
    }
}