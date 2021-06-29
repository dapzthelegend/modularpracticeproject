package com.example.core.database.characterfavorite

import androidx.lifecycle.LiveData
import androidx.room.Query

/**
 * Repository module for handling character favorite data operations [CharacterFavoriteDao]
 */
class CharacterFavoriteRepository(internal val characterFavoriteDao: CharacterFavoriteDao) {

    /**
     * Obtain a character favorites from database ordered by name
     *
     * @return [LiveData] favorite characters
     */
    fun getAllCharactersFavoriteLiveData() =
        characterFavoriteDao.getCharactersFavoriteLiveData()

    /**
     * Obtain a character favorite by identifier
     *
     * @param characterFavoriteId Unique character identifier
     *
     * @return Favorite character if exist, else null
     * @see Query
     */
    suspend fun getCharacterFavorite(characterFavoriteId: Long) =
        characterFavoriteDao.getCharacter(characterFavoriteId)

    /**
     * Obtain a character favorites from database ordered by name
     *
     * @return favorite characters
     */
    suspend fun getCharactersFavorite() =
        characterFavoriteDao.getCharactersFavorite()

    /**
     * Delete all character favorites from database
     */
    suspend fun deleteAllCharactersFavorite() =
        characterFavoriteDao.deleteAllCharactersFavorite()

    /**
     * Delete a character favorite from database by identifier
     *
     * @param characterFavoriteId Unique character identifier
     */
    suspend fun deleteCharacterFavorite(characterFavoriteId: Long) =
        characterFavoriteDao.deleteCharacterFavorite(characterFavoriteId)

    /**
     * Delete database favorite character
     *
     * @param characterFavorite Database favorite character
     */
    suspend fun deleteCharacterFavorite(characterFavorite: CharacterFavorite) =
        characterFavoriteDao.deleteCharacterFavorite(characterFavorite)

    /**
     * Insert list of favorite characters
     *
     * @param characters Favorite characters list
     */
    suspend fun insertCharactersFavorite(characters: List<CharacterFavorite>) =
        characterFavoriteDao.insertCharactersFavorites(characters)

    /**
     * Insert favorite character
     *
     * @param character Favorite Character
     */
    suspend fun insertCharacterFavorite(character: CharacterFavorite) =
        characterFavoriteDao.insertCharacterFavorite(character)
}
