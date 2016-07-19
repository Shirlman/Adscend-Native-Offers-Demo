package com.example.kb_shirlman.adscenddemo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by KB-Shirlman on 7/19/2016.
 */
public class RetrofitHelper {
    public static final int TIMEOUT = 30; // should consider our server process speed
    public static final String ADSCEND_MEDIA_API_URL = "http://adscendmedia.com/adwall/api/publisher/104363/profile/5723/offers.json";
    public static ApiInterface adscendApiInterface;

    public static ApiInterface getAdscendApiRetrofit() {
        if (adscendApiInterface == null) {
            Interceptor interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("User-Agent", "Retrofit")
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", "Basic MTA0MzYzOjE0NjQxMjE5MTc=")
                            .build();
                    return chain.proceed(newRequest);
                }
            };

            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(TIMEOUT, TimeUnit.SECONDS);
            client.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
            client.setWriteTimeout(TIMEOUT, TimeUnit.SECONDS);
            client.interceptors().add(interceptor);

            adscendApiInterface = new Retrofit.Builder()
                    .baseUrl(ADSCEND_MEDIA_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(ApiInterface.class);
        }

        return adscendApiInterface;
    }
}
