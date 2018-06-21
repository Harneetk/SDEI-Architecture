package android.sdei.com.basicapp.repository

/**
 * Created by parmil.sharma on 14/02/18.
 */
interface ServiceListener<T> {
    abstract fun getServerResponse(response: T, requestcode: Int)
    abstract fun getError(error: ErrorModel, requestcode: Int)
}