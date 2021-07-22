package com.example.characterslist.ui.list.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.characterslist.ui.list.model.CharacterItem
import com.example.characterslist.ui.list.model.CharacterItemMapper
import com.example.core.BuildConfig
import com.example.core.extensions.toMD5
import com.example.core.network.NetworkState
import com.example.core.network.services.MarvelService
import timber.log.Timber

const val PAGE_INIT_ELEMENTS = 0
const val PAGE_MAX_ELEMENTS = 50
private const val API_PUBLIC_KEY = BuildConfig.MARVEL_API_KEY_PUBLIC
private const val API_PRIVATE_KEY = BuildConfig.MARVEL_API_KEY_PRIVATE
private const val HASH_FORMAT = "%s%s%s"

/**
 * Incremental data loader for page-keyed content, where requests return keys for next/previous
 * pages. Obtaining paginated the Marvel characters.
 *
 * @see PagingSource
 */
class CharactersPageDataSource constructor(
    private val service: MarvelService,
    private val mapper: CharacterItemMapper
) : PagingSource<Int, CharacterItem>() {

    val networkState = MutableLiveData<NetworkState>()

    /**
     * Called to get the refresh key to be used for subsequent refresh calls
     *
     * @param state The state of the currently fetched data which contains the most recently
     * accessed position in the list
     * @return The refresh key
     */
    override fun getRefreshKey(state: PagingState<Int, CharacterItem>): Int = 0

    /**
     * Loading API for [PagingSource]
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterItem> {
        val position = params.key ?: PAGE_INIT_ELEMENTS
        val timestamp = System.currentTimeMillis().toString()
        if (position == PAGE_INIT_ELEMENTS) {
            Timber.e("loading")
            networkState.postValue(NetworkState.Loading())
        } else {
            networkState.postValue(NetworkState.Loading(isAdditionalRequest = true))
        }
        Timber.e(position.toString())

        return try {
            val response = service.getcharacters(
                offset = position,
                limit = PAGE_MAX_ELEMENTS,
                apiKey = API_PUBLIC_KEY,
                hash = generateApiHash(timestamp),
                timeStamp = timestamp
            )
            val data = mapper.map(response)
            val nextKey = if (data.isEmpty()) {
                null
            } else {
                position + PAGE_MAX_ELEMENTS
            }
            networkState.postValue(NetworkState.Success(isEmptyResponse = data.isEmpty()))
            LoadResult.Page(
                data = data,
                prevKey = if (position == PAGE_INIT_ELEMENTS) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            if (position == PAGE_INIT_ELEMENTS) {
                networkState.postValue(NetworkState.Error())
            } else {
                networkState.postValue(NetworkState.Error(isAdditionalRequest = true))
            }
            return LoadResult.Error(exception)
        }
    }

    // =============================================================================================
    // Private helper method
    // =============================================================================================

    /**
     * Called to generate hash key
     */
    private fun generateApiHash(timestamp: String) =
        HASH_FORMAT.format(
            timestamp,
            API_PRIVATE_KEY,
            API_PUBLIC_KEY
        ).toMD5()
}
