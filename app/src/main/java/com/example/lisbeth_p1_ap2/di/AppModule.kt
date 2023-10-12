package com.example.lisbeth_p1_ap2.di

import android.content.Context
import androidx.room.Room
import com.example.lisbeth_p1_ap2.data.DivisioneDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn( SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesDivisionDatabase(@ApplicationContext appContext: Context):DivisioneDB  =
        Room.databaseBuilder(
            appContext,
            DivisioneDB::class.java,
            "DivisioneBD.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providesTicketDao(db: DivisioneDB) = db.DivisionDao()


}