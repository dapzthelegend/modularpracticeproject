package com.example.core.network.responses

/**
 * Base response for network requests
 *
 * @param code The status code of the request
 * @param status The status of the request
 * @param message The error message of the request
 * @param data The results returned by the call
 */
data class BaseResponse<T>(
    val code: Any,
    val status: String,
    val message: String,
    val data: DataResponse<T>
)
