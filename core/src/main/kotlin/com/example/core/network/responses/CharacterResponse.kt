package com.example.core.network.responses

/**
 * Network response for marvel character
 *
 * @param id The unique ID of the character resource
 * @param name The name of the character
 * @param description A short bio or decription of the character
 * @param thumbnail The representative image of the character
 */
data class CharacterResponse(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: CharacterThumbnailResponse
)
