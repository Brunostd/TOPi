package com.deny.desafiotopi.model

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.deny.desafiotopi.adapter.UserAdapter
import com.deny.desafiotopi.api.DataService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GestorUser {

    fun getUsers(): LiveData<List<User>>{
        var listaUser = MutableLiveData<List<User>>()
        var retrofit: Retrofit = Retrofit.Builder()
            //.baseUrl("https://api.github.com/users/")
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create()).build()

        var service: DataService = retrofit.create(DataService::class.java)
        var call: Call<List<User>> = service.recuperarListaUsuario()
        //var auxListUserData: List<User> = arrayListOf()

        call.enqueue(object: Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                listaUser.value = response.body()!!
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return listaUser
    }
}