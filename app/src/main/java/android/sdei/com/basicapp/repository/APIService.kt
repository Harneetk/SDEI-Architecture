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






    @Multipart
    @POST("registration")
     fun signUP(@Part("first_name") name: RequestBody,
                        @Part("last_name") lastName: RequestBody,
                        @Part("email") email: RequestBody,@Part("password") password: RequestBody):Observable<BaseModel>





}