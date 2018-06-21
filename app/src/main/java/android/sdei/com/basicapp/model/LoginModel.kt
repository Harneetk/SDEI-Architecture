package android.sdei.com.basicapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by parmil.sharma on 17/05/18.
 */
class LoginModel : BaseModel(){
    @SerializedName("token_type")
    var token_type: String = ""

    @SerializedName("access_token")
    var access_token: String = ""

    @SerializedName("refresh_token")
    var refresh_token: String = ""

    @SerializedName("expires_in")
    var expires_in: String = ""


    fun getToken(): String{
        return token_type +" "+access_token
    }
}