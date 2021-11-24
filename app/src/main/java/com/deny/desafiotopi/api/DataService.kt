package com.deny.desafiotopi.api

import com.deny.desafiotopi.model.User
import com.deny.desafiotopi.model.UserRepository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DataService {

    @GET("/users")
    fun recuperarListaUsuario(): Call<List<User>>

    @GET("/users/{usuario}/repos")
    fun recuperarListaRepository(@Path("usuario") usuario: String): Call<List<UserRepository>>
}