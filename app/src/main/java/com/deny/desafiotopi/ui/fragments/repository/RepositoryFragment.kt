package com.deny.desafiotopi.ui.fragments.repository

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.deny.desafiotopi.MainActivity
import com.deny.desafiotopi.ui.adapter.RepositoryAdapter
import com.deny.desafiotopi.databinding.FragmentRepositoryBinding
import com.deny.desafiotopi.model.GestorUserRepository
import com.deny.desafiotopi.model.UserRepository
import retrofit2.Retrofit
import java.util.*
import kotlin.Comparator

class RepositoryFragment : Fragment() {

    private val args by navArgs<RepositoryFragmentArgs>()
    private var _binding: FragmentRepositoryBinding? = null
    private lateinit var retrofit: Retrofit

    private lateinit var repositoryViewModel: RepositoryViewModel

    private var listaRepository: List<UserRepository> = arrayListOf()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        val view = binding.root

        repositoryViewModel = ViewModelProvider(this).get(RepositoryViewModel::class.java)

        var gestor = GestorUserRepository()
        gestor.name = args.nameUsuario
        gestor.avatar = args.avatar

        repositoryViewModel.listaUserRepository = gestor.getUserRepository()

        repositoryViewModel.listaUserRepository.observe(viewLifecycleOwner, Observer { listaRepositorio ->
            //Collections.sort(listaRepositorio, SortBy())
            binding.recyclerViewRepository.adapter = RepositoryAdapter(listaRepositorio)
            binding.recyclerViewRepository.layoutManager = LinearLayoutManager(requireContext())
        })

        binding.switchClassificarMaisEstrela.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            if (binding.switchClassificarMaisEstrela.isChecked) {
                // Atualizar por mais estrela
                repositoryViewModel.listaUserRepository.observe(viewLifecycleOwner, Observer { listaRepositorio ->
                    Collections.sort(listaRepositorio, SortBy())
                    binding.recyclerViewRepository.adapter = RepositoryAdapter(listaRepositorio)
                    binding.recyclerViewRepository.adapter!!.notifyDataSetChanged()
                })
            } else {
                var intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        })

        return view
    }

    internal class SortBy : Comparator<UserRepository> {
        override fun compare(a: UserRepository, b: UserRepository): Int {
            return Integer.parseInt(b.stargazers_count).compareTo(Integer.parseInt(a.stargazers_count))
        }
    }

    /*fun recuperarListaRepository(){
        var service: DataService = retrofit.create(DataService::class.java)
        var call: Call<List<UserRepository>> = service.recuperarListaRepository(args.nameUsuario)

        call.enqueue(object: Callback<List<UserRepository>>{
            override fun onResponse(call: Call<List<UserRepository>>, response: Response<List<UserRepository>>) {
                if (response.isSuccessful){
                    listaRepository = response.body()!!

                    var i = 0
                    while (i < listaRepository.size){
                        listaRepository.get(i).avatar = args.avatar
                        i++
                    }

                    Toast.makeText(requireContext(), "Sucesso", Toast.LENGTH_SHORT).show()
                    binding.recyclerViewRepository.adapter = RepositoryAdapter(listaRepository)
                    binding.recyclerViewRepository.layoutManager = LinearLayoutManager(requireContext())
                }
            }

            override fun onFailure(call: Call<List<UserRepository>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}