package com.mozper.codingtest.presentation.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mozper.codingtest.R
import com.squareup.picasso.Picasso

object BindingUtils {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.loadImage(url: String?) {
        if (url != null) {
            Picasso.get()
                .load(url)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.mozperlogo)
                .into(this)
        }
    }
}