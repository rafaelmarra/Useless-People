package com.example.rafaelmarra.useless_people.model.dao

import com.example.rafaelmarra.useless_people.model.user.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDAO {

    fun getUser(id: Int, listener: ServiceListener) {

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
    }
}