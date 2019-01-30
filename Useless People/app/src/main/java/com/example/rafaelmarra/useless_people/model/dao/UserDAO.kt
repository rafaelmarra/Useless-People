package com.example.rafaelmarra.useless_people.model.dao

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import com.example.rafaelmarra.useless_people.model.user.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDAO {

    fun getUser(context: Context, id: Int, listener: ServiceListener) {

        if (isConnected(context)) {

            val call: Call<User> = RetrofitService.serviceFake().getUser(id)
            call.enqueue(object : Callback<User> {

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.body() != null) {
                        listener.onSucess(response.body() as User)
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    listener.onError(t)
                }
            })

        } else {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show()
        }
    }

    private fun isConnected(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

        return networkInfo?.isConnected == true
    }
}