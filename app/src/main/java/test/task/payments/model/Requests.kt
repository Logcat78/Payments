package test.task.payments.model

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface Requests {

    @Headers("app-key: 12345", "v: 1")
    @POST("login")
    suspend fun login(@Body request: LoginRequestData): LoginResponseData
    @Headers("app-key: 12345", "v: 1")
    @GET("payments")
    suspend fun payments(@Header("token") token: String): PaymentsResponseData
}