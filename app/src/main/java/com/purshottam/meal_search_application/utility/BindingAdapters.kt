package com.purshottam.meal_search_application.utility

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.purshottam.meal_search_application.R

@BindingAdapter("urlToImage")
fun urlToImage(imageView: ImageView, urlString: String?) {

    urlString.let {
        val options = RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
        Glide.with(imageView).setDefaultRequestOptions(options).load(urlString).into(imageView)
    }
}