package ssm.android.users_mobile.interactor

import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.io.IOException

object OkInteractor {
    private var client = OkHttpClient()
    private var request: Request? = null
    private var url: HttpUrl? = null
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    private fun requestHttp(res: (String?) -> Unit, err: (String?) -> Unit) {
        request?.let { httpRequest ->
            client.newCall(httpRequest).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) = res(response.body?.string())

                override fun onFailure(call: Call, e: IOException) {
                    err(e.localizedMessage)
                }
            })
        }
    }

    fun getUsers(res: (String?) -> Unit, err: (String?) -> Unit) {
        url = "$BASE_URL/users".toHttpUrlOrNull()?.newBuilder()?.build()
        url?.let { httpUrl ->
            request = Request.Builder()
                .url(httpUrl)
                .build()
        }

        requestHttp(res, err)
    }
}