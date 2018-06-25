package android.sdei.com.basicapp.utill


import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.databinding.BindingAdapter
import android.graphics.*
import android.media.ExifInterface
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.sdei.com.basicapp.R
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AlertDialog
import android.text.*
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.assist.FailReason
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
import org.json.JSONObject
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 * it contain all the common methods that are uses as per requirement
 */


var emailregex2 = "@[.*\\[]+[0-9]+:[A-Za-z0-9\\s]+[.*\\]]"
 @BindingAdapter("errorText")
 fun setErrorMessage(view: TextInputLayout, errorMessage: String) {
    view.error = errorMessage
}

 @BindingAdapter("textChangeListener")
 fun setTextChangeListener(view: TextInputLayout,enabled: Boolean) : Unit? {
    return view.editText?.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (s.length > 0) {
                view.setErrorEnabled(false)
            }
            else{
                view.setErrorEnabled(true)
            }
        }
        override fun afterTextChanged(s: Editable) {}
    })
}

 @BindingAdapter("passwordChangeListener")
 fun setPasswordChangeListener(view: TextInputLayout,enabled: Boolean) : Unit? {
    return view.editText?.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (s.length > 0) {
                view.setErrorTextAppearance(R.style.error_appearance)
                if(isPasswordValid(s.toString())) {
                    view.setErrorEnabled(false)
                }
                else{
                     view.setErrorEnabled(true)
                    setErrorMessage(view,"Password should contain minimun 8 characters, 1 uppercase letter, 1 lowercase letter, 1 number, 1 special character.")
                }
            }
            else{
                view.setErrorTextAppearance(R.style.error_appearance_gray)
                view.setErrorEnabled(true)
            }
        }
        override fun afterTextChanged(s: Editable) {}
    })
}

 fun isPasswordValid( input: CharSequence): Boolean {
    val p = Pattern.compile("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$&*]).*$", Pattern.CASE_INSENSITIVE)
    val m = p.matcher(input)
    return m.matches()
}

 fun showToast(context: Context, message: String) {
    val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}

 fun showDialog(context: Context,title: String,message : String){
    AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(context.getString(R.string.abc_action_mode_done), DialogInterface.OnClickListener {
                dialog, which ->dialog.dismiss()  })
            .show()
 }

 fun parseError(result: String?): String {
    var errorDescription = ""
    try {
        if (result != null) {
            val jsonObjectResult = JSONObject(result)
            errorDescription = jsonObjectResult.getString("message")
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
        errorDescription = result.toString()
    }

    return errorDescription

}


fun getUserNameInitial(userName: String): String {
    var nameInitial = ""
    if (!userName.isEmpty()) {
        val name = userName.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (name.size > 1) {
            nameInitial = name[0].substring(0, 1) + name[1].substring(0, 1)
        } else if (name.size == 1) {
            nameInitial = name[0].substring(0, 1)
        }
    }
    return nameInitial.toUpperCase()
}


/**
 * This method is used to get formatted date
 *
 * @param smsTimeInMillis contains time in milliseconds
 * @return returns today, yesterday or date
 */
fun getFormattedDate(smsTimeInMillis: Long): String {
    val smsTime = Calendar.getInstance()
    smsTime.timeInMillis = smsTimeInMillis

    val now = Calendar.getInstance()

    if (now.get(Calendar.DATE) == smsTime.get(Calendar.DATE)) {
        return "Today "
    } else if (now.get(Calendar.DATE) - smsTime.get(Calendar.DATE) == 1) {
        return "Yesterday "
    } else {

        val mDate = Date(smsTimeInMillis)
        val sdf = SimpleDateFormat("MM/dd")
        return sdf.format(mDate)
    }
}


fun getDate(date: String): String {
    try {
        val dateL = java.lang.Long.valueOf(date)!!
        val mDate = Date(dateL)
        val sdf = SimpleDateFormat("h:mm a MM/dd")
        return sdf.format(mDate)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return ""
}


fun getDateStemp(date: String): Date {
    val dateL = java.lang.Long.valueOf(date)!!
    return Date(dateL)
}



fun dpToPx(dp: Float, context: Context): Float {
    return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)


}




/**
 * pad -- used to change the date and month from single digit to double
 * digit value(9 to 09)
 *
 * @param c -- int value need to be pad
 * @return padded integer value
 */
fun pad(c: Int): String {
    return if (c >= 10)
        c.toString()
    else
        "0" + c.toString()
}




fun getRealPathFromURI(context: Context, contentUri: Uri): String {
    val cursor = context.contentResolver.query(contentUri, null, null, null, null)
    if (cursor == null) {
        return contentUri.path
    } else {
        cursor.moveToFirst()
        val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        return cursor.getString(idx)
    }
}

fun compressImage(context: Context, imageUri: Uri): String {
    val filePath = getRealPathFromURI(context, imageUri)

    var scaledBitmap: Bitmap? = null
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    var bmp = BitmapFactory.decodeFile(filePath, options)

    var actualHeight = options.outHeight//2988
    var actualWidth = options.outWidth //5312

    val maxHeight = 1200.0f
    val maxWidth = 1200.0f
    /*float maxHeight = 816.0f;
        float maxWidth = 612.0f;*/
    var imgRatio = (actualWidth / actualHeight).toFloat()
    val maxRatio = maxWidth / maxHeight

    if (actualHeight > maxHeight || actualWidth > maxWidth) {
        if (imgRatio < maxRatio) {
            imgRatio = maxHeight / actualHeight
            actualWidth = (imgRatio * actualWidth).toInt()
            actualHeight = maxHeight.toInt()
        } else if (imgRatio > maxRatio) {
            imgRatio = maxWidth / actualWidth
            actualHeight = (imgRatio * actualHeight).toInt()
            actualWidth = maxWidth.toInt()
        } else {
            actualHeight = maxHeight.toInt()
            actualWidth = maxWidth.toInt()

        }
    }

    options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight)
    options.inJustDecodeBounds = false
    options.inDither = false
    options.inPurgeable = true
    options.inInputShareable = true
    options.inTempStorage = ByteArray(16 * 1024)

    try {
        bmp = BitmapFactory.decodeFile(filePath, options)
    } catch (exception: OutOfMemoryError) {
        exception.printStackTrace()

    }

    try {
        scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888)
    } catch (exception: OutOfMemoryError) {
        exception.printStackTrace()
    }

    val ratioX = actualWidth / options.outWidth.toFloat()
    val ratioY = actualHeight / options.outHeight.toFloat()
    val middleX = actualWidth / 2.0f
    val middleY = actualHeight / 2.0f

    val scaleMatrix = Matrix()
    scaleMatrix.setScale(ratioX, ratioY, middleX, middleY)

    val canvas = Canvas(scaledBitmap!!)
    canvas.matrix = scaleMatrix
    canvas.drawBitmap(bmp, middleX - bmp.width / 2, middleY - bmp.height / 2, Paint(Paint.FILTER_BITMAP_FLAG))


    val exif: ExifInterface
    try {
        exif = ExifInterface(filePath)

        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0)
        Log.d("EXIF", "Exif: $orientation")
        val matrix = Matrix()
        if (orientation == 6) {//|| orientation == 0) {
            matrix.postRotate(90f)
            Log.d("EXIF", "Exif: $orientation")
        }/*else if (orientation == 0) {
                matrix.postRotate(0);
                Log.d("EXIF", "Exif: " + orientation);
            }  */
        else if (orientation == 3) {
            matrix.postRotate(180f)
            Log.d("EXIF", "Exif: $orientation")
        } else if (orientation == 8) {
            matrix.postRotate(270f)
            Log.d("EXIF", "Exif: $orientation")
        }
        scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.width, scaledBitmap.height, matrix, true)
    } catch (e: IOException) {
        e.printStackTrace()
    }

    var out: FileOutputStream? = null
    val filename = getFilename()
    try {
        out = FileOutputStream(filename)
        scaledBitmap!!.compress(Bitmap.CompressFormat.JPEG, 80, out)

    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    }

    return filename

}

fun getFilename(): String {
    val file = File(Environment.getExternalStorageDirectory().path, "DocsInk/Images")
    if (!file.exists()) {
        file.mkdirs()
    }
    return file.absolutePath + "/" + System.currentTimeMillis() + ".jpg"

}

fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    val height = options.outHeight
    val width = options.outWidth
    var inSampleSize = 1

    if (height > reqHeight || width > reqWidth) {
        val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
        val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())
        inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
    }
    val totalPixels = (width * height).toFloat()
    val totalReqPixelsCap = (reqWidth * reqHeight * 2).toFloat()

    while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
        inSampleSize++
    }

    return inSampleSize
}


fun setImage(imagePath: String, imageViewPatientImage: ImageView, option: DisplayImageOptions, progressBar: ProgressBar?, context: Context) {
    ImageLoader.getInstance().displayImage(imagePath.trim { it <= ' ' }, imageViewPatientImage, option, object : SimpleImageLoadingListener() {
       override fun onLoadingStarted(imageUri: String, view: View) {
            if (progressBar != null) {
                progressBar.visibility = View.VISIBLE
            }
       }
        override fun onLoadingFailed(imageUri: String, view: View, failReason: FailReason) {}
        override  fun onLoadingComplete(imageUri: String, view: View, loadedImage: Bitmap) {
            if (progressBar != null) {
                progressBar.visibility = View.GONE
            }
        }
    })
}



fun isImageFile(imageType: String): Boolean {
    return if (imageType.equals("image/jpeg", ignoreCase = true) || imageType.equals("image/jpg", ignoreCase = true) || imageType.equals("image/png", ignoreCase = true) || imageType.equals("image/gif", ignoreCase = true)) {
        true
    } else false
}


private fun openBrowser(link: String,context: Context) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        context.startActivity(intent)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

}



fun hideSoftKeyboard(context: Context, et: EditText) {
    try {
        val imm = context.getSystemService(
                Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et.windowToken, 0)
    } catch (e: Exception) {
    }

}

