package dev.kutluca.vlchallenge.data.network

import dev.kutluca.vlchallenge.model.network.response.character.CharactersNetworkResponse
import dev.kutluca.vlchallenge.util.network.HttpApiFactory
import okhttp3.Interceptor
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {
    /**
     * Fetch characters list from the endpoint, with the page number.
     * @param page - Page number.
     *
     * @return Characters response with info and list items.
     */
    @GET("character")
    suspend fun fetchCharacters(
        @Query("page") page: Int = 1,
    ): CharactersNetworkResponse

    companion object {
        fun create(
            baseUrl: String,
            httpInterceptors: List<Interceptor> = emptyList(),
        ): CharactersApi = HttpApiFactory.buildApi(baseUrl, httpInterceptors)
    }
}