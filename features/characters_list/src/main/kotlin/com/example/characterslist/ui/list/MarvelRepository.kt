package com.example.characterslist.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.characterslist.ui.list.model.CharacterItem
import com.example.characterslist.ui.list.model.CharacterItemMapper
import com.example.characterslist.ui.list.paging.CharactersPageDataSource
import com.example.core.BuildConfig
import com.example.core.extensions.toMD5
import com.example.core.network.responses.BaseResponse
import com.example.core.network.responses.CharacterResponse
import com.example.core.network.services.MarvelService
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

private const val API_PUBLIC_KEY = BuildConfig.MARVEL_API_KEY_PUBLIC
private const val API_PRIVATE_KEY = BuildConfig.MARVEL_API_KEY_PRIVATE
private const val HASH_FORMAT = "%s%s%s"

/**
 * Repository module for handling marvel API network operations [MarvelService]
 */
class MarvelRepository @Inject constructor(
    private val service: MarvelService,
    mapper: CharacterItemMapper
) {

    val pageDataSource: MutableLiveData<CharactersPageDataSource> =
        MutableLiveData(CharactersPageDataSource(service, mapper))
    /**
     * Get all the info of Marvel Character
     *
     * @param id Unique identifier of a Marvel Character
     * @return Response for a single character resource
     */
    suspend fun getCharacter(id: Long): BaseResponse<CharacterResponse> {
        val timestamp = System.currentTimeMillis().toString()
        return service.getcharacter(
            characterId = id,
            apiKey = API_PUBLIC_KEY,
            hash = generateApiHash(timestamp),
            timeStamp = timestamp
        )
    }

    /**
     * Get all Marvel Characters by paged.
     *
     * @return Response for a paged characters resource
     */
    fun getCharacters(): Flow<PagingData<CharacterItem>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = true),
            pagingSourceFactory = { pageDataSource.value!! }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
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
