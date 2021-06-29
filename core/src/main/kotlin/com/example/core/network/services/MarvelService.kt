package com.example.core.network.services

import com.example.core.network.responses.BaseResponse
import com.example.core.network.responses.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Api service class for the Marvel API Endpoints
 */
interface MarvelService {

    companion object {
        const val CHARACTERS = "/v1/public/characters"
        const val CHARACTER = "/v1/public/characters/{characterId}"
    }

    /**
     * Retrieves a list of Characters
     *
     * @param apiKey The unique identifier used to identify all calling to an API.
     * @param timeStamp A digital current record of the time
     * @param offset Skip the specified number of resources in the result set
     * @param limit Limit the result to the specified number of resources
     * @return Response for the comic characters resource
     */
    @GET(CHARACTERS)
    suspend fun getcharacters(
        @Query("apiKey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timeStamp: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): BaseResponse<CharacterResponse>

    /**
     * Retrieves a specified marvel charcter by it's unique identifier
     *
     * @param characterId The unique ID of the character resource
     * @param apiKey The unique identifier used to identify all calling to an API.
     * @param timeStamp A digital current record of the time
     * @return Response for the comic characters resource
     */
    @GET(CHARACTER)
    suspend fun getcharacter(
        @Path("characterId") characterId: Long,
        @Query("apiKey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timeStamp: String,
    ): BaseResponse<CharacterResponse>
}
