package android.sdei.com.basicapp.utill

import android.app.Application
import android.content.Context
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.QueueProcessingType
import com.nostra13.universalimageloader.core.download.BaseImageDownloader

/**
 * Created by parmil.sharma on 06/06/18.
 */
class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        initImageLoader(this)
    }

    // This configuration tuning is custom. You can tune every option, you may tune some of them,
    // or you can create default configuration by
    //  ImageLoaderConfiguration.createDefault(this);
    // method.
    fun initImageLoader(context: Context) {

        val config = ImageLoaderConfiguration.Builder(context)
        //        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.threadPriority(Thread.MAX_PRIORITY)
        config.imageDownloader(BaseImageDownloader(context, 3000, 3000))
        config.denyCacheImageMultipleSizesInMemory()
        config.diskCacheFileNameGenerator(Md5FileNameGenerator())
        config.diskCacheSize(50 * 1024 * 1024) // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO)
        // config.writeDebugLogs(); // Remove for release app
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build())
    }
}