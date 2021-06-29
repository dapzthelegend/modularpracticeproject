package com.example.core.database.characterfavorite

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing a Character Favorite resource
 *
 * @see Entity
 */
@Entity(tableName = "character_favorite")
data class CharacterFavorite(
    @PrimaryKey val id: Long,
    val name: String,
    val imageUrl: String
)
