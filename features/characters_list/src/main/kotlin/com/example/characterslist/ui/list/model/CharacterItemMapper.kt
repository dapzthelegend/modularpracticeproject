package com.example.characterslist.ui.list.model

import com.example.core.mapper.Mapper
import com.example.core.network.responses.BaseResponse
import com.example.core.network.responses.CharacterResponse

private const val IMAGE_URL_FORMAT = "%s.%s"

/**
 * Helper class to transform network response to visual model for catching the necessary data
 *
 * @see Mapper
 */
class CharacterItemMapper : Mapper<BaseResponse<CharacterResponse>, List<CharacterItem>> {

    /**
     * Tranform network response to [CharacterItem]
     *
     * @param from network response
     * @return list of parsed character item
     */
    override suspend fun map(from: BaseResponse<CharacterResponse>): List<CharacterItem> =
        from.data.results.map {
            CharacterItem(
                id = it.id,
                name = it.name,
                description = it.description,
                imageUrl = IMAGE_URL_FORMAT.format(
                    it.thumbnail.path.replace("http", "https"),
                    it.thumbnail.extension
                )
            )
        }
}
