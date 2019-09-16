package com.sdei.sdeiarchitecture.repository.networkOperator;


import com.sdei.sdeiarchitecture.BuildConfig;
import com.sdei.sdeiarchitecture.helper.dagger.scope.AppScope;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Scope;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@AppScope
public class NetworkAdapter {

    public static NetworkAdapter cInstance;
    public ApiService apiService;

    public String demo() {
        return "HEllossssssssssssss";
    }

    /* Static 'instance' method */
    public static NetworkAdapter getInstance() {
        if (cInstance == null) {
            cInstance = new NetworkAdapter();
            cInstance.setupRetrofitClient();
            return cInstance;
        }
        cInstance.setupRetrofitClient();
        return cInstance;
    }

    public static void initInstance() {
        if (cInstance == null) {
            cInstance = new NetworkAdapter();
            cInstance.setupRetrofitClient();
        }
    }

    public void setupRetrofitClient() {

        try {
            Interceptor HEADER_INTERCEPTOR = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Content-Type", "application/json");
                    requestBuilder.method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            };


            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(HEADER_INTERCEPTOR)
                    .cache(null)
                    .connectTimeout(60, TimeUnit.MINUTES)
                    .readTimeout(60, TimeUnit.MINUTES).build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            apiService = retrofit.create(ApiService.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ApiService getNetworkServices() {
        return apiService;
    }
}
