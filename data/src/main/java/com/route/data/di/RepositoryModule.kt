package com.route.data.di

import com.route.data.api.NetworkHandler
import com.route.data.api.NewsServices
import com.route.data.database.NewsDao
import com.route.data.repositories.NewsRepositoryImpl
import com.route.data.repositories.local.LocalDataSourceImpl
import com.route.data.repositories.remote.RemoteDataSourceImpl
import com.route.domain.repositories.NewsRepository
import com.route.domain.repositories.local.LocalDataSource
import com.route.domain.repositories.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun provideRemoteDataSource(
        newsServices: NewsServices
    ): RemoteDataSource {
        return RemoteDataSourceImpl(newsServices)
    }

    @Provides
    fun provideLocalDataSource(
        newsDao: NewsDao
    ): LocalDataSource {
        return LocalDataSourceImpl(newsDao)
    }

    @Provides
    fun provideNewsRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        networkHandler: NetworkHandler
    ): NewsRepository {
        return NewsRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            networkHandler = networkHandler
        )
    }

}
