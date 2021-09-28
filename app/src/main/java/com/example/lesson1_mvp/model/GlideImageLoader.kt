package com.example.lesson1_mvp.model

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.lesson1_mvp.view.IImageLoader

class GlideImageLoader :
    IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}