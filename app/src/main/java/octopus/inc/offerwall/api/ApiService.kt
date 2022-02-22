package octopus.inc.offerwall.api

import octopus.inc.offerwall.api.model.ObjectIdsResponse
import octopus.inc.offerwall.api.model.ObjectResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("entities/getAllIds")
    suspend fun getAllIds(): Response<ObjectIdsResponse>

    @Headers("Content-Type: application/json")
    @GET("object/{id}")
    suspend fun getObjectById(
        @Path("id") id: String
    ): Response<ObjectResponse>
}