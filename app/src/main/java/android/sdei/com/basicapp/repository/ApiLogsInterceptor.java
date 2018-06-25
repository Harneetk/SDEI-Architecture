package android.sdei.com.basicapp.repository;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import timber.log.Timber;
/*
* @ApiLogsInterceptor class use to print the logs of input and output parameters
   of the apis
 *
* */
public class ApiLogsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        Timber.i("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers());
        Log.v("Headers" ,""+request.headers());

        Log.v("Request_Parameters" ,""+bodyToString(request));
        Log.v("Method Type" ,"  "+request.method()
                +"  "+request.url());


        Response response = chain.proceed(request);

        ResponseBody responseBody = response.body();
        String responseBodyString = response.body().string();

        // now we have extracted the response body but in the process
        // we have consumed the original reponse and can't read it again
        // so we need to build a new one to return from this method

        Response newResponse = response.newBuilder().body(ResponseBody.create(responseBody.contentType(), responseBodyString.getBytes())).build();

       String   request_response = "Url:"+request.url()+"\n Request Parameters "+bodyToString(request)+"\n Response"+responseBodyString;

        long t2 = System.nanoTime();

        if(newResponse.code()!=200)
        {
            Crashlytics.log(request_response);

        }


        return newResponse;
    }

    private static String bodyToString(final Request request){

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}