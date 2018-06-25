package android.sdei.com.basicapp.model


import com.google.gson.annotations.SerializedName
import java.security.Provider
/**
 *
 * @Base User class :  This class contain the all the fields having required for registration of the user..
 *
 *
 */



class User {
    @SerializedName("id")
    var id: String = ""

    @SerializedName("email")
    var email: String? = ""

    @SerializedName("first_name")
    var first_name: String? = ""

    @SerializedName("last_name")
    var last_name: String? = ""

    @SerializedName("org_id")
    var org_id: String? = ""

    @SerializedName("user_type_id")
    var user_type_id: String? = ""

    @SerializedName("active")
    var active: String? = ""

    @SerializedName("image")
    var image: String? = ""

    @SerializedName("phone_number")
    var phone_number: String? = ""

    @SerializedName("department_id")
    var department_id: String? = ""

    @SerializedName("specialty")
    var specialty: String? = ""

    @SerializedName("office_id")
    var office_id: String? = ""

    @SerializedName("is_master")
    var is_master: String? = ""

    @SerializedName("chat_status")
    var chat_status: String? = ""

    @SerializedName("verified")
    var verified: String? = ""

    @SerializedName("salesforce_id")
    var salesforce_id: String? = ""

    @SerializedName("patient")
    var patient: String? = ""



    fun getName(): String{
        return first_name+ " "+last_name
    }



}