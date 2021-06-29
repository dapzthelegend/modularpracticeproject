package com.example.core.network.repositories

import com.example.core.BuildConfig
import com.example.core.extensions.toMD5
import com.example.core.network.responses.BaseResponse
import com.example.core.network.responses.CharacterResponse
import com.example.core.network.services.MarvelService

private const val API_PUBLIC_KEY = BuildConfig.MARVEL_API_KEY_PUBLIC
private const val API_PRIVATE_KEY = BuildConfig.MARVEL_API_KEY_PRIVATE
private const val HASH_FORMAT = "%s%s%s"

/**
 * Repository module for handling marvel API network operations [MarvelService]
 */
class MarvelRepository(internal val marvelService: MarvelService) {
    /**
     * Get all the info of Marvel Character
     *
     * @param id Unique identifier of a Marvel Character
     * @return Response for a single character resource
     */
    suspend fun getCharacter(id: Long): BaseResponse<CharacterResponse> {
        val timestamp = System.currentTimeMillis().toString()
        return marvelService.getcharacter(
            characterId = id,
            apiKey = API_PUBLIC_KEY,
            hash = generateApiHash(timestamp),
            timeStamp = timestamp
        )
    }

    /**
     * Get all Marvel Characters by paged.
     *
     * @param offset Skip the specified number of resources in the result set
     * @param limit Limit the result set to the specified number of resources
     * @return Response for a paged characters resource
     */
    suspend fun getCharacters(offset: Int, limit: Int): BaseResponse<CharacterResponse> {
        val timestamp = System.currentTimeMillis().toString()
        return marvelService.getcharacters(
            offset = offset,
            limit = limit,
            apiKey = API_PUBLIC_KEY,
            hash = generateApiHash(timestamp),
            timeStamp = timestamp
        )
    }

    // ============================================================================================
    //  Private generators methods
    // ============================================================================================

    /**
     * Generate a md5 digest of the timestamp parameter, private API key and public.
     *
     * @param timestamp A digital current record of the time.
     * @return The MD5 Hash
     */
    private fun generateApiHash(timestamp: String) =
        HASH_FORMAT.format(timestamp, API_PRIVATE_KEY, API_PUBLIC_KEY).toMD5()
}
