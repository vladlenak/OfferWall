package octopus.inc.offerwall.api.model

import com.google.gson.annotations.SerializedName

data class TextResponse(
    @SerializedName("id") val id: Double,
    @SerializedName("type") val type: String,
    @SerializedName("message") val message: String
)