package com.example.book_tracker.data.network

import retrofit2.http.GET

// 使用 Quotable API 获取励志名言
data class QuoteResponse(
    val content: String,
    val author: String,
    val tags: List<String>
)

interface QuoteApiService {
    @GET("random")
    suspend fun getRandomQuote(): QuoteResponse

    @GET("random?tags=education|success|learning")
    suspend fun getMotivationalQuote(): QuoteResponse
}
