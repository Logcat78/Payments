package test.task.payments.model

data class LoginResponseData(
    val success: String,
    val response: ResponseData
)

data class ResponseData(
    val token: String
)