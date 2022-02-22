package octopus.inc.offerwall.api.model

import com.google.gson.annotations.SerializedName

data class ObjectResponse(
    @SerializedName("id") val id: Double,
    @SerializedName("type") val type: String,
    @SerializedName("message") val message: String,
    @SerializedName("url") val url: String
)