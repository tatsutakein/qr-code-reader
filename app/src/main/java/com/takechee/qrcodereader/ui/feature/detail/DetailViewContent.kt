package com.takechee.qrcodereader.ui.feature.detail

import android.graphics.Bitmap
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.*
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.GroupieViewHolder

private enum class DetailViewContent {
    QR_IMAGE,
    TEXT,
    ACTION_AREA,
    FAVORITE,
    EDIT_NICKNAME;

    val id: Long = (ordinal + 1).toLong()
}

interface DetailViewContentEventListener {
    fun onShareActionClick()
    fun onOpenIntentActionClick()
    fun onOpenUrlActionClick()
    fun onCopyToClipBoardActionClick()
    fun onEditNicknameClick()
    fun onFavoriteClick(isFavorite: Boolean)
}


// =============================================================================================
//
// QRImage
//
// =============================================================================================
data class DetailViewContentQRImage(
    val bitmap: Bitmap
) : BindableItem<ItemDetailViewContentQrimageBinding>(DetailViewContent.QR_IMAGE.id) {
    override fun getLayout(): Int = R.layout.item_detail_view_content_qrimage
    override fun bind(viewBinding: ItemDetailViewContentQrimageBinding, position: Int) {
        viewBinding.qrCodeImageView.setImageBitmap(bitmap)
    }
}


// =============================================================================================
//
// Text
//
// =============================================================================================
data class DetailViewContentText(
    val text: String
) : BindableItem<ItemDetailViewContentTextBinding>(DetailViewContent.TEXT.id) {
    override fun getLayout(): Int = R.layout.item_detail_view_content_text
    override fun bind(viewBinding: ItemDetailViewContentTextBinding, position: Int) {
        viewBinding.text = text
    }
}


// =============================================================================================
//
// ActionArea
//
// =============================================================================================
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


// =============================================================================================
//
// Editor
//
// =============================================================================================
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

data class DetailViewFavorite(
    val isFavorite: Boolean,
    val eventListener: DetailViewContentEventListener
) : BindableItem<ItemDetailViewContentFavoriteBinding>(DetailViewContent.FAVORITE.id) {
    override fun getLayout(): Int = R.layout.item_detail_view_content_favorite
    override fun bind(viewBinding: ItemDetailViewContentFavoriteBinding, position: Int) {
        val animView = viewBinding.iconImageView
        if (isFavorite) {
            animView.post { animView.playAnimation() }
        } else {
            animView.cancelAnimation()
            animView.progress = 0f
        }
        viewBinding.isFavorite = isFavorite
        viewBinding.eventListener = eventListener
    }
}