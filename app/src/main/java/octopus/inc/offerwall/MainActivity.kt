package octopus.inc.offerwall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.beust.klaxon.Klaxon
import com.google.gson.Gson
import octopus.inc.offerwall.api.model.TextResponse
import octopus.inc.offerwall.api.model.WebViewResponse
import octopus.inc.offerwall.databinding.ActivityMainBinding
import octopus.inc.offerwall.viewmodel.MainViewModel
import org.json.JSONObject
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.allIds.observe(this) {
            getObjectById(it.data[0].id)
        }

        viewModel.currentObject.observe(this) {
            val jsonStr = it.toString()
            Log.d(TAG, "jsonStr: $jsonStr")

            if (jsonStr.contains("type=text")) {
                binding.webView.visibility = View.GONE

                binding.textView.text = ""
            } else if (jsonStr.contains("type=webview")) {
//                val webViewResponse = Gson().fromJson(jsonStr, WebViewResponse::class.java)
//                val result = Klaxon().parse<WebViewResponse>("""{"id":2,"type":"webview","url": "https://cp.appotrack.space"}""")
                val result = Klaxon().parse<WebViewResponse>(jsonStr)

                binding.webView.loadUrl(result?.url!!)
            }
        }

        viewModel.getAllIds()

    }

    private fun getObjectById(id: Long) {
        viewModel.getObjectById(id)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}