package android.sdei.com.basicapp.repository

import android.sdei.com.basicapp.model.BaseModel
import android.sdei.com.basicapp.model.LoginResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.FieldMap
import retrofit2.http.PUT
import retrofit2.http.FormUrlEncoded




interface APIService
{

    /**

     * @Base APIService interface :  This interface contain the all the mehtods
        of apis (Communicate to  servers with prdefined parameters ).
     **/


    @POST("authenticate")
    @FormUrlEncoded
    fun login(@Field("username") userName: String, @Field("password") password: String): Observable<LoginResponse>

    @POST("password/forgot")
    @FormUrlEncoded
    fun forgotPassword(@Field("email") email: String): Observable<BaseModel>

    @POST("devicetokens")
    @FormUrlEncoded
    fun saveDeviceToken(@Header("Authorization") authToken: String,@Field("token") token: String,@Field("device") device: String): Observable<String>

    @POST("client_identifier")
    @FormUrlEncoded
    fun getSaltKey(@Field("ssid") deviceId: String): Observable<String>



    @Multipart
    @POST("registration")
     fun signUP(@Part("first_name") name: RequestBody,
                        @Part("last_name") lastName: RequestBody,
                        @Part("email") email: RequestBody,@Part("password") password: RequestBody):Observable<BaseModel>





}