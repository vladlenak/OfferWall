package octopus.inc.offerwall.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import octopus.inc.offerwall.api.ApiService
import octopus.inc.offerwall.api.model.ObjectIdsResponse
import octopus.inc.offerwall.api.RetrofitClient
import octopus.inc.offerwall.api.model.ObjectResponse
import java.lang.Exception

class MainViewModel : ViewModel() {

    var index: Int = 0
    var objectIdsResponse = MutableLiveData<ObjectIdsResponse>()
    val objectResponse = MutableLiveData<ObjectResponse>()
    private val api = RetrofitClient.getRetrofit(BASE_URL)?.create(ApiService::class.java)

    fun getObjectIdsResponse() {
        viewModelScope.launch {
            try {
                val res = api?.getAllIds()
                res?.let {
                    if (it.isSuccessful) {
                        objectIdsResponse.value = it.body()
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, "Exception: ${e.message}")
            }
        }
    }

    fun getObjectById(id: String) {
        viewModelScope.launch {
            try {
                val res = api?.getObjectById(id)

                res?.let { it ->
                    if (it.isSuccessful) {
                        val responseBody = it.body()
                        Log.d(TAG, "getObjectById: $responseBody")
                        objectResponse.value = it.body()

                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, "Exception: $e")
            }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
        private const val BASE_URL = "https://demo3005513.mockable.io/api/v1/"
    }
}