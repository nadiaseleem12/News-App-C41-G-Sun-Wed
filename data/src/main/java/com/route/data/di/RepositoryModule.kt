package com.route.data.di

import com.route.data.api.NewsServices
import com.route.data.repositories.remote.RemoteDataSourceImpl
import com.route.data.repositories.NewsRepositoryImpl
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
    fun provideOnlineDataSource(
        newsServices: NewsServices
    ): RemoteDataSource {
        return RemoteDataSourceImpl(newsServices)
    }

    @Provides
    fun provideNewsRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(localDataSource = localDataSource, remoteDataSource = remoteDataSource)
    }

}
