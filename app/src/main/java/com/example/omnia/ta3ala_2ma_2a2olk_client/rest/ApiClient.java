package com.example.omnia.ta3ala_2ma_2a2olk_client.rest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://t3ala2ma2olk.herokuapp.com";
    static private Retrofit retrofit;
    static private OkHttpClient httpClient;
    static private Retrofit.Builder builder;
    public static Retrofit getApiClient() {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(MyApplication.getContext().getCacheDir(), cacheSize);

        httpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {

                        if (!isNetworkAvalaiable()) {
                            CacheControl.Builder cacheBuilder = new CacheControl.Builder();

                            cacheBuilder.maxStale(365, TimeUnit.DAYS);
                            cacheBuilder.onlyIfCached();
                            return chain.proceed(chain.request().newBuilder().cacheControl(cacheBuilder.build()).build());
                        }

                        return chain.proceed(chain.request());
                    }
                })
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        CacheControl.Builder cacheBuilder = new CacheControl.Builder();

                        if (isNetworkAvalaiable()) {
                            cacheBuilder.maxAge(10, TimeUnit.SECONDS);
                            okhttp3.Response response = chain.proceed(chain.request());
                            return response.newBuilder()
                                    .removeHeader("Pragma")
                                    .removeHeader("Cache-Control")
                                    .header("Cache-Control", cacheBuilder.build().toString())
                                    .build();
                        }

                        return chain.proceed(chain.request());
                    }
                }).build();


        builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(httpClient).build();
        return retrofit;
    }

    public static boolean isNetworkAvalaiable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}