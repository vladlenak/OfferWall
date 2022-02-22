package octopus.inc.offerwall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import octopus.inc.offerwall.databinding.ActivityMainBinding
import octopus.inc.offerwall.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.objectResponse.observe(this) {
            if (it.type == "text") {
                Log.d(TAG, "text")
                binding.webView.visibility = View.GONE
                binding.textView.visibility = View.VISIBLE
                binding.textView.text = it.message
            } else if (it.type == "webview") {
                Log.d(TAG, "webview")
                it?.url?.let{ url ->
                    binding.webView.loadUrl(url)
                    binding.textView.visibility = View.GONE
                    binding.webView.visibility = View.VISIBLE

                }
            } else if (it.type == "image") {
                Log.d(TAG, "image")
                it?.url?.let{ url ->
                    binding.webView.loadUrl(url)
                    binding.textView.visibility = View.GONE
                    binding.webView.visibility = View.VISIBLE

                }
            } else {
                binding.textView.visibility = View.GONE
                binding.webView.visibility = View.GONE
            }
        }

        viewModel.objectIdsResponse.observe(this) {
            getObject()
        }

        viewModel.getObjectIdsResponse()


        binding.next.setOnClickListener {
            viewModel.objectIdsResponse.value?.data?.size?.let {
                if (viewModel.index+1 < it) {
                    viewModel.index = viewModel.index + 1
                    Log.d(TAG, "index = ${viewModel.index}")
                } else {
                    viewModel.index = 0
                }
            }

            getObject()
        }

    }

    private fun getObject() {
        viewModel.objectIdsResponse.value?.data?.get(viewModel.index)?.id?.let {
            viewModel.getObjectById(it)
        }
    }


    companion object {
        private const val TAG = "MainActivity"
    }
}