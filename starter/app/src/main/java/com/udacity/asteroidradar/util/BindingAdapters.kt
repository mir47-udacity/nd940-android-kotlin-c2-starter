package com.udacity.asteroidradar.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R

/**
 * Binding adapter used to display images from URL using Picasso
 */
@BindingAdapter(value = ["imageUrl", "callback"], requireAll = false)
fun setImageUrl(imageView: ImageView, imageUrl: String?, callback: ImageLoadCallback? = null) {
    // Note: Picasso could be replaced with Glide (https://bumptech.github.io/glide/)
    // or Coil (https://github.com/coil-kt/coil), however the project instructions
    // recommend to use Picasso for this project.
    Picasso.with(imageView.context).load(imageUrl)
        .placeholder(R.drawable.placeholder)
        .into(imageView, object: Callback {
            override fun onSuccess() = callback?.onImageLoaded(true) ?: Unit
            override fun onError() = callback?.onImageLoaded(false) ?: Unit
        })
}

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}