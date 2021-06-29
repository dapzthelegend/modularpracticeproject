package com.example.core.network.responses

/**
 * Character Thumbnail class
 *
 *@param path The directory path of the image
 *@param extension The image file extension
 */
data class CharacterThumbnailResponse(
    val path: String,
    val extension: String
)
