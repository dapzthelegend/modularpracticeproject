package com.example.core.di.modules

import com.example.core.BuildConfig
import com.example.core.di.CoreComponent
import com.example.core.network.repositories.MarvelRepository
import com.example.core.network.services.MarvelService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class that contributes network objects to the object graph [CoreComponent]
 *
 * @see Module
 */
@Module
class NetworkModule {

    /**
     * Create a provider method binding for [HttpLoggingInterceptor]
     *
     * @return Instance of [HttpLoggingInterceptor]
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    /**
     * Create a provider method binding for [OkHttpClient]
     *
     * @return instance of [OkHttpClient]
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(interceptor)
        }
        return httpClient.build()
    }

    /**
     * Create a provider method binding for [Retrofit]
     *
     * @return Instance of [Retrofit]
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideRetrofitBuilder(httpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BuildConfig.MARVEL_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

    /**
     * Create provider binding method for [MarvelService]
     *
     * @return Instance of [MarvelService]
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideMarvelService(retrofit: Retrofit) =
        retrofit.create(MarvelService::class.java)

    /**
     * Create provider binding method for [MarvelRepository]
     *
     * @return Instance of [MarvelRepository]
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideMarvelRepository(marvelService: MarvelService) =
        MarvelRepository(marvelService)
}
