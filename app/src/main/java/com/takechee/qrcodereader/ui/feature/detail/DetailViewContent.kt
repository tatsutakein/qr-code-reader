package com.takechee.qrcodereader.ui.feature.detail

import android.graphics.Bitmap
import androidx.databinding.ViewDataBinding
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.*
import com.xwray.groupie.databinding.BindableItem

private enum class DetailViewContent {
    QR_IMAGE,
    TITLE,
    TEXT,
    ACTION_AREA,
    EDIT_NICKNAME;

    val id: Long = (ordinal + 1).toLong()
}

interface DetailViewContentEventListener {
    fun onShareActionClick()
    fun onOpenIntentActionClick()
    fun onOpenUrlActionClick()
    fun onCopyToClipBoardActionClick()
    fun onEditNicknameClick()
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
) : BindableItem<ItemDetailViewContentTitleBinding>(DetailViewContent.TITLE.id) {
    override fun getLayout(): Int = R.layout.item_detail_view_content_title
    override fun bind(viewBinding: ItemDetailViewContentTitleBinding, position: Int) {
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

data class DetailViewContentEditNickname(
    val nickname: String,
    val eventListener: DetailViewContentEventListener
) : BindableItem<ItemDetailViewContentEditNicknameBinding>(DetailViewContent.EDIT_NICKNAME.id) {
    override fun getLayout(): Int = R.layout.item_detail_view_content_edit_nickname
    override fun bind(viewBinding: ItemDetailViewContentEditNicknameBinding, position: Int) {
        viewBinding.nickname = nickname
        viewBinding.eventListener = eventListener
    }
}

sealed class DetailViewContentActionArea<T : ViewDataBinding> :
    BindableItem<T>(DetailViewContent.ACTION_AREA.id) {

    data class UrlAction(
        val eventListener: DetailViewContentEventListener
    ) : DetailViewContentActionArea<ItemDetailViewContentUrlActionBinding>() {
        override fun getLayout(): Int = R.layout.item_detail_view_content_url_action
        override fun bind(viewBinding: ItemDetailViewContentUrlActionBinding, position: Int) {
            viewBinding.eventListener = eventListener
        }
    }

    data class TextAction(
        val eventListener: DetailViewContentEventListener
    ) : DetailViewContentActionArea<ItemDetailViewContentTextActionBinding>() {
        override fun getLayout(): Int = R.layout.item_detail_view_content_text_action
        override fun bind(viewBinding: ItemDetailViewContentTextActionBinding, position: Int) {
            viewBinding.eventListener = eventListener
        }
    }

    data class SpecifiedAction(
        val eventListener: DetailViewContentEventListener
    ) : DetailViewContentActionArea<ItemDetailViewContentSpecifiedActionBinding>() {
        override fun getLayout(): Int = R.layout.item_detail_view_content_specified_action
        override fun bind(
            viewBinding: ItemDetailViewContentSpecifiedActionBinding,
            position: Int
        ) {
            viewBinding.eventListener = eventListener
        }
    }
}