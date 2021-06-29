package com.example.core.di.modules

import android.content.Context
import androidx.room.Room
import com.example.core.BuildConfig
import com.example.core.database.MarvelDatabase
import com.example.core.database.characterfavorite.CharacterFavoriteDao
import com.example.core.database.characterfavorite.CharacterFavoriteRepository
import com.example.core.di.CoreComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Class that contributes to the object graph [CoreComponent]
 *
 * @see Module
 */
@Module
class DatabaseModule {

    /**
     * Create a provider method binding for [MarvelDatabase]
     *
     * @return Instance of [MarvelDatabase]
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideMarvelDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            MarvelDatabase::class.java,
            BuildConfig.MARVEL_DATABASE_NAME
        ).build()

    /**
     * Create a provider method binding for [CharacterFavoriteDao]
     *
     * @return Instance of [CharacterFavoriteDao]
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideCharacterFavoriteDao(marvelDatabase: MarvelDatabase) =
        marvelDatabase.characterFavoriteDao()

    /**
     * Create a provider method binding for [CharacterFavoriteRepository]
     *
     * @return Instance of [CharacterFavoriteRepository]
     * @see Provides
     */
    fun provideCharacterFavoriteRepository(characterFavoriteDao: CharacterFavoriteDao) =
        CharacterFavoriteRepository(characterFavoriteDao)
}
