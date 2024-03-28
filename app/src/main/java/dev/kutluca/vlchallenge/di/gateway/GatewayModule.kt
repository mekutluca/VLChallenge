package dev.kutluca.vlchallenge.di.gateway

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.kutluca.vlchallenge.gateway.characters.CharactersGateway
import dev.kutluca.vlchallenge.gateway.characters.CharactersGatewayImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class GatewayModule {

    @Binds
    abstract fun bindApiGateway(
        restApiGateway: CharactersGatewayImpl
    ): CharactersGateway
}