package com.findmyip.di


import com.findmyip.data.network.IpApiService
import com.findmyip.data.repository.IpRepositoryImpl
import com.findmyip.data.source.RemoteDataSource
import com.findmyip.data.source.RemoteDataSourceImpl
import com.findmyip.domain.IpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideIpApiService(): IpApiService {
        return Retrofit.Builder()
            .baseUrl("https://ipapi.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IpApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: IpApiService): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideIpRepository(remoteDataSource: RemoteDataSource): IpRepository {
        return IpRepositoryImpl(remoteDataSource)
    }
}