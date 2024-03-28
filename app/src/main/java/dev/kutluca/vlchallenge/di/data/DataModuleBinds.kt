package dev.kutluca.vlchallenge.di.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.kutluca.vlchallenge.repository.characters.CharactersRepository
import dev.kutluca.vlchallenge.repository.characters.CharactersRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModuleBinds {
    @Binds
    @Singleton
    abstract fun bindCharactersRepository(
        charactersRepositoryImpl: CharactersRepositoryImpl,
    ): CharactersRepository
}