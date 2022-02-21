package octopus.inc.offerwall.api.model

import com.beust.klaxon.Json

data class WebViewResponse(
    @Json("id") val id: String,
    @Json("type") val type: String,
    @Json("url") val url: String
)