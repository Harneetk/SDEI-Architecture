package com.sdei.sdeiarchitecture.utils

import android.Manifest.permission.*
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.afollestad.assent.Permission
import com.afollestad.assent.askForPermissions
import com.afollestad.assent.runWithPermissions
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.callback.PermissionCallback
import com.sdei.sdeiarchitecture.utils.base.BaseActivity
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


/**
 * Picasso for image loading ...
 */
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

/**
 * For Creating Video thumbnail from video URI ...
 */
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

/**
 * Function for Single Permission ...
 */
fun initSinglePermission(activity: Activity, permission: String, callback: PermissionCallback) {
    Dexter.withActivity(activity)
        .withPermission(permission)
        .withListener(object : PermissionListener {
            override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                callback.onPermissionGranted()
            }

            override fun onPermissionRationaleShouldBeShown(
                permission: PermissionRequest?,
                token: PermissionToken?
            ) {

            }

            override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                callback.onPermissionRejected()
            }
        }
        )
        .withErrorListener { error ->
            Log.e("Dexter", "There was an error: $error")
            callback.onPermissionRejected()
        }.check()
}

/**
 * Function for Multiple Permissions ...
 */
fun initMultiPermissions(activity: Activity, callback: PermissionCallback) {
    Dexter.withActivity(activity)
        .withPermissions(
            CAMERA,
            READ_CONTACTS,
            RECORD_AUDIO
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {/* ... */
                callback.onPermissionGranted()
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: List<PermissionRequest>,
                token: PermissionToken
            ) {/* ... */
                callback.onPermissionRejected()
            }
        }).check()
}

fun checkMyPermission(baseActivity: BaseActivity<*, *>) {
    baseActivity.askForPermissions(Permission.WRITE_EXTERNAL_STORAGE, Permission.CAMERA) { result ->
        // Check the result, see the Using Results section
    }
}

fun runWithPermissions(baseActivity: BaseActivity<*, *>) {
    baseActivity.runWithPermissions(
        Permission.WRITE_EXTERNAL_STORAGE,
        Permission.CAMERA
    ) { result ->
        // Do something
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("concatData")
fun concatData(view: TextView, concatData: String) {
    Log.e("sadfads","asasdfasdfadsfasd")
    view.text = "Item $concatData"
}









