package dev.kutluca.vlchallenge.di.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.kutluca.vlchallenge.data.network.CharactersApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteApiModule {

    @Provides
    @Singleton
    fun provideCharactersApi(): CharactersApi =
        CharactersApi.create("https://rickandmortyapi.com/api/")
}