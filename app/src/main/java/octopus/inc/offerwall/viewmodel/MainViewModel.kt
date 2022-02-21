package octopus.inc.offerwall.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beust.klaxon.Klaxon
import kotlinx.coroutines.launch
import octopus.inc.offerwall.api.ApiService
import octopus.inc.offerwall.api.model.MockableResponse
import octopus.inc.offerwall.api.RetrofitClient
import octopus.inc.offerwall.api.model.WebViewResponse
import java.lang.Exception

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {

    val allIds = MutableLiveData<MockableResponse>()
    val currentObject = MutableLiveData<Any>()
    private val api = RetrofitClient.getRetrofit(BASE_URL)?.create(ApiService::class.java)

    fun getAllIds() {
        viewModelScope.launch {
            try {
                val res = api?.getAllIds()
                res?.let {
                    if (it.isSuccessful) {
                        allIds.value = it.body()
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, "Exception: ${e.message}")
            }
        }
    }

    fun getObjectById(id: Long) {
        viewModelScope.launch {
            try {
                val res = api?.getObjectById(id)

                res?.let {
                    if (it.isSuccessful) {
                        val responseBody = it.body()
                        Log.d(TAG, "getObjectById: $responseBody")
                        currentObject.value = it.body()
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, "Exception: $e")
            }
        }
    }

    companion object {
        private const val BASE_URL = "https://demo3005513.mockable.io/api/v1/"
    }
}