package android.sdei.com.basicapp.repository

import android.sdei.com.basicapp.model.BaseModel
import android.sdei.com.basicapp.model.LoginModel
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import retrofit2.http.FieldMap
import retrofit2.http.PUT
import retrofit2.http.FormUrlEncoded



/**
 * Created by parmil.sharma on 14/02/18.
 */
interface APIService {

    @POST("login")
    @FormUrlEncoded
    fun login(@Field("email") userName: String, @Field("password") password: String): Observable<LoginModel>

    @POST("password/forgot")
    @FormUrlEncoded
    fun forgotPassword(@Field("email") email: String): Observable<BaseModel>

    @POST("devicetokens")
    @FormUrlEncoded
    fun saveDeviceToken(@Header("Authorization") authToken: String,@Field("token") token: String,@Field("device") device: String): Observable<String>

    @POST("client_identifier")
    @FormUrlEncoded
    fun getSaltKey(@Field("ssid") deviceId: String): Observable<String>

    @POST("register")
    @FormUrlEncoded
    fun register(@Field("email") email: String, @Field("password") password: String,
                          @Field("first_name") first_name: String,@Field("last_name") last_name: String): Observable<LoginModel>


    @PUT("users/{user_id}")
    @FormUrlEncoded
    fun editUser(@Header("Authorization") token: String,@Path("user_id") user_id: String, @FieldMap fields: Map<String, String>): Observable<String>



}