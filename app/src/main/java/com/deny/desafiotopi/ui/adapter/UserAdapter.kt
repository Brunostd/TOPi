package com.deny.desafiotopi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.deny.desafiotopi.R
import com.deny.desafiotopi.model.User
import com.deny.desafiotopi.ui.fragments.home.HomeFragmentDirections
import jp.wasabeef.glide.transformations.CropCircleTransformation

class UserAdapter(var listaUsuarios: List<User>): RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(user: User){
            var avatar: ImageView = itemView.findViewById(R.id.imageViewImageUsuario)
            var nomeUser: TextView = itemView.findViewById(R.id.textViewNomeUsuario)
            var itemRow: CardView = itemView.findViewById(R.id.itemRow)
            var url: String = user.avatar_url

            var options =  RequestOptions()
            options.placeholder(R.drawable.ic_menu_camera)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(R.drawable.ic_menu_gallery)
                .transform(CropCircleTransformation());

            Glide.with(itemView).load(url).apply(options).into(avatar)
            nomeUser.setText(user.login)

            itemRow.setOnClickListener(View.OnClickListener {
                val action = HomeFragmentDirections.actionNavHomeToRepositoryFragment(nomeUser.text.toString(), url)
                itemView.findNavController().navigate(action)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_users, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listaUsuarios[position])
    }

    override fun getItemCount(): Int = listaUsuarios.size
}