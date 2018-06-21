package android.sdei.com.basicapp.utill

import android.graphics.Bitmap
import android.sdei.com.basicapp.model.LoginModel
import android.sdei.com.basicapp.model.User
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer
import java.util.*

/**
 * Created by parmil.sharma on 01/06/18.
 */
object AppInstance {
    var loginModel: LoginModel?= null
    var userModel: User?= null
    var CURRENT_CHAT_ID = ""
    init {}

    fun getDisplayImageOptions(default_image: Int): DisplayImageOptions {
         var option=DisplayImageOptions.Builder()
                if(default_image!=-1) {
                    option.showImageOnLoading(default_image)
                    option.showImageForEmptyUri(default_image)
                    option.showImageOnFail(default_image)
                }
        option.cacheOnDisk(true).cacheInMemory(true)
        option.imageScaleType(ImageScaleType.EXACTLY)
        option.bitmapConfig(Bitmap.Config.RGB_565)
        option.considerExifParams(true)
        option.displayer(FadeInBitmapDisplayer(300))
        return option.build()
    }
}