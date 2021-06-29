package com.example.core.database.characterfavorite

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Data access object class for [CharacterFavorite]
 *
 * @see Dao
 */
@Dao
interface CharacterFavoriteDao {

    /**
     * Obtain a character favorite by identifier
     *
     * @param characterFavoriteId Unique character identifier
     *
     * @return Favorite character if exist, else null
     * @see Query
     */
    @Query("SELECT * FROM character_favorite WHERE id= :characterFavoriteId")
    suspend fun getCharacter(characterFavoriteId: Long): CharacterFavorite?

    /**
     * Obtain a character favorites from database ordered by name
     *
     * @return [LiveData] favorite characters
     * @see Query
     */
    @Query("SELECT * FROM character_favorite ORDER BY name")
    fun getCharactersFavoriteLiveData(): LiveData<List<CharacterFavorite>>

    /**
     * Obtain a character favorites from database ordered by name
     *
     * @return favorite characters
     * @see Query
     */
    @Query("SELECT * FROM character_favorite ORDER BY name")
    suspend fun getCharactersFavorite(): List<CharacterFavorite>

    /**
     * Delete all character favorites from database
     */
    @Query("DELETE FROM character_favorite")
    suspend fun deleteAllCharactersFavorite()

    /**
     * Delete a character favorite from database by identifier
     *
     * @param characterFavoriteId Unique character identifier
     */
    @Query("DELETE FROM character_favorite WHERE id = :characterFavoriteId")
    suspend fun deleteCharacterFavorite(characterFavoriteId: Long)

    /**
     * Delete database favorite character
     *
     * @param characterFavorite Database favorite character
     *
     * @see Delete
     */
    @Delete
    suspend fun deleteCharacterFavorite(characterFavorite: CharacterFavorite)

    /**
     * Insert list of favorite characters
     *
     * @param characters Favorite characters list
     *
     * @see Insert
     */
    @Insert
    suspend fun insertCharactersFavorites(characters: List<CharacterFavorite>)

    /**
     * Insert favorite character
     *
     * @param character Favorite Character
     *
     * @see Insert
     */
    @Insert
    suspend fun insertCharacterFavorite(character: CharacterFavorite)
}
