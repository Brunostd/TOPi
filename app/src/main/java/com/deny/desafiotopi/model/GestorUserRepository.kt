package com.deny.desafiotopi.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import com.deny.desafiotopi.api.DataService
import com.deny.desafiotopi.ui.repository.RepositoryFragmentArgs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GestorUserRepository {

    lateinit var name: String
    lateinit var avatar: String

    fun getUserRepository(): LiveData<List<UserRepository>>{
        var listaUserRepository = MutableLiveData<List<UserRepository>>()
        var retrofit: Retrofit = Retrofit.Builder()
            //.baseUrl("https://api.github.com/users/")
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create()).build()

        var service: DataService = retrofit.create(DataService::class.java)
        var call: Call<List<UserRepository>> = service.recuperarListaRepository(name)

        call.enqueue(object : Callback<List<UserRepository>>{
            override fun onResponse(
                call: Call<List<UserRepository>>,
                response: Response<List<UserRepository>>
            ) {
                if(response.isSuccessful){
                    listaUserRepository.value = response.body()!!

                    var i = 0
                    while (i < listaUserRepository.value!!.size){
                        listaUserRepository.value!!.get(i).avatar = avatar
                        i++
                    }
                }
            }

            override fun onFailure(call: Call<List<UserRepository>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return listaUserRepository
    }

}