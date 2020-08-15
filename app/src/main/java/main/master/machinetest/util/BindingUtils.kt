package main.master.machinetest.util


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

@BindingAdapter("image")
fun loadImage(view: ImageView, image: String) {
    Glide.with(view)
        .load(image)
        .into(view)

 //   Picasso.get().load(image).into(view)
}