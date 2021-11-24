package com.deny.desafiotopi.api

import com.deny.desafiotopi.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("{usuario}")
    fun recuperarUsuario(@Path("usuario") usuario: String): Call<User>

}