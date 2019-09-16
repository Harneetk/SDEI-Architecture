package com.sdei.sdeiarchitecture.utils

import android.content.Context
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.sdei.sdeiarchitecture.R
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

@BindingAdapter("loadImage", "placeholder")
fun xloadImages(
    view: ImageView?,
    image: String?,
    placeHolder: Int
) {
    image?.let {
        if (it.contains("http")) {
            Picasso.get()
                .load(it)
                .error(R.drawable.ic_launcher_background)
                .placeholder(placeHolder)
                .into(view)
        } else {
            val file = File(it)
            Picasso.get()
                .load(file)
                .error(placeHolder)
                .placeholder(placeHolder)
                .into(view)
        }
    } ?: run {
        Picasso.get()
            .load(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view)
    }
}

fun createVideoThumbnail(context: Context, uri: String): File {
    Log.e("createVideoThumbnail", "" + uri)
    val bitmap =
        ThumbnailUtils.createVideoThumbnail(uri, MediaStore.Images.Thumbnails.MINI_KIND)
    return persistImage(context, bitmap, "thumbnail")
}

fun persistImage(context: Context, bitmap: Bitmap, name: String): File {
    val filesDir = context.filesDir
    val imageFile = File(filesDir, "$name.jpg")

    val os: OutputStream
    try {
        os = FileOutputStream(imageFile) as OutputStream
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
        os.flush()
        os.close()
    } catch (e: Exception) {
        Log.e("AppBinding", "Error writing bitmap", e)
    }

    return imageFile
}



