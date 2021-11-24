package com.deny.desafiotopi.ui.fragments.home

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deny.desafiotopi.MainActivity
import com.deny.desafiotopi.model.GestorUser
import com.deny.desafiotopi.model.User

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


    var gestorUser: GestorUser = GestorUser()
    val listaUser: LiveData<List<User>> = gestorUser.getUsers()
}