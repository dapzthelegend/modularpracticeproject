package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.BuildConfig
import com.example.core.database.characterfavorite.CharacterFavorite
import com.example.core.database.characterfavorite.CharacterFavoriteDao

/**
 * Marvel room database storing different requested info like: comic, characters etc
 *
 * @see Database
 */
@Database(
    entities = [CharacterFavorite::class],
    exportSchema = BuildConfig.MARVEL_DATABASE_EXPORT_SCHEMA,
    version = BuildConfig.MARVEL_DATABASE_VERSION
)
abstract class MarvelDatabase : RoomDatabase() {

    /**
     * Get Character Favorite data access object
     *
     * @return Instance of [CharacterFavoriteDao]
     */
    abstract fun characterFavoriteDao(): CharacterFavoriteDao
}
