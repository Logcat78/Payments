package test.task.payments.model

data class PaymentsResponseData(
    val success: String,
    val response: List<ResponseDataList>

)


data class ResponseDataList(
    val id: Int,
    val title: String,
    val amount: Any,
    val created: Int
)
