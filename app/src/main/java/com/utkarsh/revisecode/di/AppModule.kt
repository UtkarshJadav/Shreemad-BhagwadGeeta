package com.utkarsh.revisecode.di

import android.content.Context
import androidx.room.Room
import androidx.viewbinding.BuildConfig
import com.farmit.data.local.database.RoomDatabase
import com.farmit.data.local.pref.EncPref
import com.farmit.data.local.pref.PreferenceManager
import com.utkarsh.revisecode.data.local.database.Database
import com.utkarsh.revisecode.data.local.database.DatabaseManager
import com.utkarsh.revisecode.data.local.pref.Preference
import com.utkarsh.revisecode.data.remote.Api
import com.utkarsh.revisecode.data.remote.ApiManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAppEncSharedPref(@ApplicationContext context: Context): EncPref {
        return EncPref.Builder()
            .serPrefName(context.packageName)
            .setContext(context)
            .setDebuggable(BuildConfig.DEBUG)
            .build()
    }

    @Singleton
    @Provides
    fun provideAppRoomDatabase(@ApplicationContext context: Context): RoomDatabase {
        return Room.databaseBuilder(
            context,
            RoomDatabase::class.java,
            "app.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideAppPreference(encPref: EncPref): Preference {
        return PreferenceManager(encPref)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(roomDatabase: RoomDatabase): Database {
        return DatabaseManager(roomDatabase)
    }

    @Singleton
    @Provides
    fun provideAppApi(preference: Preference): Api {
        return ApiManager(preference)
    }
}