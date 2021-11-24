package com.deny.desafiotopi.ui.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.deny.desafiotopi.model.GestorUserRepository
import com.deny.desafiotopi.model.UserRepository

class RepositoryViewModel: ViewModel() {

    //var gestorUserRepository: GestorUserRepository = GestorUserRepository()
    lateinit var listaUserRepository: LiveData<List<UserRepository>>
}