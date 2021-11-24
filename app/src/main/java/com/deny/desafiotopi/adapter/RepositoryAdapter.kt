package com.deny.desafiotopi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.deny.desafiotopi.R
import com.deny.desafiotopi.model.UserRepository
import jp.wasabeef.glide.transformations.CropCircleTransformation

class RepositoryAdapter(var listaRepository: List<UserRepository>): RecyclerView.Adapter<RepositoryAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(userRepository: UserRepository){
            var avatar: ImageView = itemView.findViewById(R.id.imageViewRepository)
            var nameRepository: TextView = itemView.findViewById(R.id.textViewNomeRepository)
            var descriptionRepository: TextView = itemView.findViewById(R.id.textViewDescriptionRepository)
            var forkRepository: TextView = itemView.findViewById(R.id.textViewForkRepository)
            var starRepository: TextView = itemView.findViewById(R.id.textViewStarRepository)
            var languageRepository: TextView = itemView.findViewById(R.id.textViewLanguageRepository)
            var url: String = userRepository.avatar

            var options =  RequestOptions()
            options.placeholder(R.drawable.ic_menu_camera)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(R.drawable.ic_menu_gallery)
                .transform(CropCircleTransformation());

            Glide.with(itemView).load(url).apply(options).into(avatar)
            nameRepository.setText(userRepository.name)
            descriptionRepository.setText(userRepository.description)
            forkRepository.setText(userRepository.forks_count.toString())
            starRepository.setText(userRepository.stargazers_count.toString())
            languageRepository.setText(userRepository.language)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_repository, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryAdapter.MyViewHolder, position: Int) {
       holder.bind(listaRepository[position])
    }

    override fun getItemCount(): Int = listaRepository.size
}