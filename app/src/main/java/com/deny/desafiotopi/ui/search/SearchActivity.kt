package com.deny.desafiotopi.ui.search

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.deny.desafiotopi.MainActivity
import com.deny.desafiotopi.ui.adapter.UserAdapter
import com.deny.desafiotopi.databinding.ActivitySearchBinding
import com.deny.desafiotopi.model.User
import com.deny.desafiotopi.ui.fragments.home.HomeViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recebePesquisa: String
    private var auxList: List<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        hendleSearch(getIntent())

        homeViewModel.listaUser.observe(this, Observer { listaUsuario ->
            var i = 0
            while (i < listaUsuario.size){
                if (listaUsuario.get(i).login.equals(recebePesquisa)){
                    auxList = listOf(listaUsuario.get(i))
                }
                i++
            }
            binding.recyclerViewSearch.adapter = UserAdapter(auxList)
            binding.recyclerViewSearch.layoutManager = GridLayoutManager(applicationContext, 2)
        })

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setContentView(view)
    }
    fun hendleSearch(intent: Intent){
        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
            var q: String = intent.getStringExtra(SearchManager.QUERY)!!
            supportActionBar!!.setTitle(q)
            Log.d("resultado", "resultado: "+q)
            recebePesquisa = q
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val myIntent = Intent(applicationContext, MainActivity::class.java)
        startActivityForResult(myIntent, 0)
        finish()
        return true
    }
}