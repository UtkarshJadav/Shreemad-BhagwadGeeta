package com.utkarsh.sbg.data.remote


import com.utkarsh.sbg.BuildConfig
import com.utkarsh.sbg.data.local.pref.Preference
import com.utkarsh.sbg.data.models.ChaptersModelItem
import com.utkarsh.sbg.data.models.VersesListModelItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiManager(preference: Preference) : Api {

    private val apiService: ApiService by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(
                        "x-rapidapi-host",
                        "bhagavad-gita3.p.rapidapi.com"
                    )
                    .addHeader(
                        "x-rapidapi-key",
                        "315cf74f7bmsh8bdb17bd12768a1p1dc47cjsn2d18a3d4ba10"
                    )
                    .build()
                return@addInterceptor chain.proceed(request)
            }
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
        return@lazy Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    override suspend fun getChaptersList(): ApiResponse<List<ChaptersModelItem>> {
        return executeApiHelper { apiService.getChaptersList() }
    }

    override suspend fun getVersesOfChapter(chapter_number: Int): ApiResponse<List<VersesListModelItem>> {
        return executeApiHelper { apiService.getVersesOfChapter(chapter_number) }
    }
}



