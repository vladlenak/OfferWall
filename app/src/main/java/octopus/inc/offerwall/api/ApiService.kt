package octopus.inc.offerwall.api

import kotlinx.coroutines.flow.Flow
import octopus.inc.offerwall.api.model.MockableResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("entities/getAllIds")
    suspend fun getAllIds(): Response<MockableResponse>

    @Headers("Content-Type: application/json")
    @GET("object/{id}")
    suspend fun getObjectById(
        @Path("id") id: Long
    ): Response<Any>
}