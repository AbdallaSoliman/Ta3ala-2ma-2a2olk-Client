package com.example.omnia.ta3ala_2ma_2a2olk_client.rest;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCategories;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
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
                            cacheBuilder.maxAge(1, TimeUnit.SECONDS);
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

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(httpClient).client(client).build();
        return retrofit;
    }

    public static boolean isNetworkAvalaiable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//  NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()){
            return true;
        }
        return false;
    }
}