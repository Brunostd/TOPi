package com.deny.desafiotopi.ui.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.deny.desafiotopi.ui.adapter.UserAdapter
import com.deny.desafiotopi.databinding.FragmentHomeBinding
import com.deny.desafiotopi.model.User
import retrofit2.Retrofit
import java.util.*
import kotlin.Comparator
import com.deny.desafiotopi.MainActivity


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private var listaUsers: List<User> = arrayListOf()
    private var auxAdapter = UserAdapter(listaUsers)

    private lateinit var retrofit: Retrofit

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.listaUser.observe(viewLifecycleOwner, Observer { listaUsuario ->
            //Collections.sort(listaUsuario, SortBy())
            listaUsers = listaUsuario
            binding.recyclerViewUser.adapter = UserAdapter(listaUsuario)
            binding.recyclerViewUser.layoutManager = GridLayoutManager(requireContext(), 2)
        })

        binding.switchOrdemAlfabetica.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            if (binding.switchOrdemAlfabetica.isChecked) {
                // Atualizar ordem alfabetica
                homeViewModel.listaUser.observe(viewLifecycleOwner, Observer { listaUsuario ->
                    Collections.sort(listaUsuario, SortBy())
                    binding.recyclerViewUser.adapter = UserAdapter(listaUsuario)
                    binding.recyclerViewUser.adapter!!.notifyDataSetChanged()
                })
            } else {
                var intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        })

        return root
    }

    internal class SortBy : Comparator<User> {
        override fun compare(a: User, b: User): Int {
            return a.login.compareTo(b.login)
        }
    }

    /*fun recuperarListaUserRetrofit(){
        var service: DataService = retrofit.create(DataService::class.java)
        var call: Call<List<User>> = service.recuperarListaUsuario()

        call.enqueue(object: Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful){
                    listaUsers = response.body()!!
                    Toast.makeText(requireContext(), "Sucesso", Toast.LENGTH_SHORT).show()
                    binding.recyclerViewUser.adapter = UserAdapter(listaUsers)
                    binding.recyclerViewUser.layoutManager = GridLayoutManager(requireContext(), 2)
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(requireContext(), "Falha", Toast.LENGTH_SHORT).show()
            }

        })
    }*/

    /*fun recuperarUserRetrofit(){
        var userRetrofit: UserService = retrofit.create( UserService::class.java )
        var call: Call<User> = userRetrofit.recuperarUsuario("iluwatar")

        call.enqueue(object: Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful){
                    var user: User? = response.body()
                    binding.textViewRetrofit.setText(user?.login + " / "+ user?.avatar_url)
                }
            }

            call.enqueue(object: Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful){
                    listaUsers = response.body()!!
                    Toast.makeText(requireContext(), "Sucesso", Toast.LENGTH_SHORT).show()
                    binding.recyclerViewUsers.adapter = UserAdapter(listaUsers)
                    binding.recyclerViewUsers.layoutManager = GridLayoutManager(requireContext(), 2)
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(requireContext(), "Falha", Toast.LENGTH_SHORT).show()
            }

        })

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}