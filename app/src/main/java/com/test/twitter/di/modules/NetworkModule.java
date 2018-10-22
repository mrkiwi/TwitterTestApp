package com.test.twitter.di.modules;

import android.text.TextUtils;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.test.twitter.data.Constants;
import com.test.twitter.data.network.service.AuthService;
import com.test.twitter.data.network.service.CommonApiService;
import com.test.twitter.presentation.utils.PreferencesManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NetworkModule {


    @Named("add_token")
    @Singleton
    @Provides
    public Interceptor provideAddTokenInterceptor(PreferencesManager preferencesManager) {
        return chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            if (preferencesManager.isAccessTokenExist()) {
                requestBuilder.header(Constants.HEADER_AUTHORIZATION, String.format(Constants.HEADER_AUTHORIZATION_BEARER_VALUE, preferencesManager.getAccessToken()));
            } else {
                requestBuilder.header(Constants.HEADER_AUTHORIZATION, String.format(Constants.HEADER_AUTHORIZATION_BASIC_VALUE, "b25QQXNTdmNmYzVRNFp5NlgxclpWWkU4eDpOQm9QRHZzNVV5cXFzdEdJUkV5NlJqWG44UlhhNGJDREF5cVo5TUZ2cm1YTFF2SEpVQQ=="));
                requestBuilder.header("Content-type", "application/x-www-form-urlencoded");
            }
            return chain.proceed(requestBuilder.build());
        };
    }

    @Named("get_token")
    @Singleton
    @Provides
    public Interceptor provideGetTokenInterceptor(PreferencesManager preferencesManager) {
        return chain -> {
            Response originalResponse = chain.proceed(chain.request());
            String token = originalResponse.header(Constants.HEADER_AUTHORIZATION);
            if (!TextUtils.isEmpty(token)) preferencesManager.saveAccessToken(token);
            return originalResponse;
        };
    }

    @Named("no_token")
    @Provides
    public OkHttpClient provideNoTokenHttpClient(@Named("add_token") Interceptor addTokenInterceptor) {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Timber.d(message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(addTokenInterceptor);

        builder.addInterceptor(new StethoInterceptor());
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }

    @Named("token")
    @Provides
    public OkHttpClient provideTokenOkHttpClient(@Named("add_token") Interceptor addTokenInterceptor,
                                                 @Named("get_token") Interceptor getTokenInterceptor) {

        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Timber.d(message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(addTokenInterceptor)
                .addInterceptor(getTokenInterceptor)
                .addInterceptor(loggingInterceptor);

        builder.addInterceptor(new StethoInterceptor());
        return builder.build();
    }

    @Named("token")
    @Provides
    public Retrofit provideTokenRetrofit(@Named("token") OkHttpClient tokenOkHttpClient,
                                         Converter.Factory gsonConverterFactory,
                                         CallAdapter.Factory rxJava2CallAdapterFactory) {

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_SERVER_URL)
                .client(tokenOkHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build();
    }


    @Named("no_token")
    @Provides
    public Retrofit provideNoTokenRetrofit(@Named("no_token") OkHttpClient noTokenOkHttpClient,
                                           Converter.Factory gsonConverterFactory,
                                           CallAdapter.Factory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_SERVER_URL)
                .client(noTokenOkHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build();
    }

    @Singleton
    @Provides
    public Converter.Factory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    public CallAdapter.Factory provideRxCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public AuthService provideAuthService(@Named("no_token") Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }

    @Singleton
    @Provides
    public CommonApiService provideCommonApiService(@Named("token") Retrofit retrofit) {
        return retrofit.create(CommonApiService.class);
    }
}
