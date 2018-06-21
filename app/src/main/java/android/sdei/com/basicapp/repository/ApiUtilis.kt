package android.sdei.com.basicapp.repository


/**
 * Created by parmil.sharma on 14/02/18.
 */
class ApiUtilis {
    companion object {
        val BASE_URL = "https://devpatient.docsink.com"
        val WEB_SERVICE_URL = BASE_URL + "/api/"

        fun getAPIService(): APIService {
            return RetrofitClient.getClient(WEB_SERVICE_URL).create(APIService::class.java)
        }
    }
}