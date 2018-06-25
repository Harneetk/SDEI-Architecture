package android.sdei.com.basicapp.repository

import android.util.Log
import com.crashlytics.android.Crashlytics
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by parmil.sharma on 14/02/18.
 */
class ApiUtilis {
    companion object {
        val BASE_URL = "http://52.34.207.5:4048/user/"
        private val httpClient = OkHttpClient.Builder()


        private val builder = Retrofit.Builder()
                .baseUrl(BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())


        fun getAPIService(): APIService {

            val logging = TimberLoggingInterceptor()
             httpClient.addInterceptor(logging)
             val client = httpClient.connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS).build()
            val retrofit = builder.client(client).build()
            return retrofit.create(APIService::class.java)


        }


    }











}