package android.sdei.com.basicapp.repository

import android.sdei.com.basicapp.model.BaseModel
import android.sdei.com.basicapp.model.LoginModel
import android.sdei.com.basicapp.model.LoginResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.FieldMap
import retrofit2.http.PUT
import retrofit2.http.FormUrlEncoded



/**
 * Created by parmil.sharma on 14/02/18.
 */
interface APIService {

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


  /*  inputMap.put("first_name", signup.fName)
    inputMap.put("last_name", isNull(signup.lName))
    inputMap.put("email", signup.email)
    inputMap.put("location", isNull(signup.location))
    inputMap.put("gender", signup.getGender())
    inputMap.put("contact", signup.contact)
    inputMap.put("password", signup.password)
    inputMap.put("zipCode", signup.zip)
    inputMap.put("dob", signup.getDateOfBirth())
    inputMap.put("user_type", isNull(signup.user_type))
    inputMap.put("facebook_id", signup.facebook_id)*/


    @Multipart
    @POST("registration")
     fun signUP(@Part("first_name") name: RequestBody,
                        @Part("last_name") lastName: RequestBody,
                        @Part("email") email: RequestBody,@Part("password") password: RequestBody):Observable<BaseModel>



    /*@POST("registration")
    @FormUrlEncoded
    fun register(@Field("email") email: String, @Field("password") password: String,
                          @Field("first_name") first_name: String,@Field("last_name") last_name: String): Observable<LoginModel>
*/


}