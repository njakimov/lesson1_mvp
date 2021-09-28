package com.example.lesson1_mvp.view.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1_mvp.databinding.ItemUserBinding
import com.example.lesson1_mvp.model.GithubRepositoryRepo
import com.example.lesson1_mvp.presentation.IUserListPresenter
import com.example.lesson1_mvp.view.IImageLoader
import com.example.lesson1_mvp.view.UserItemView

class UsersRVAdapter(
    private val presenter: IUserListPresenter,
    val imageLoader: IImageLoader<ImageView>
) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), imageLoader
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }
    }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    class ViewHolder(private val vb: ItemUserBinding, val imageLoader: IImageLoader<ImageView>) :
        RecyclerView.ViewHolder(vb.root), UserItemView {

        override var pos: Int = -1

        override fun showLogin(login: String) {
            vb.tvLogin.text = login
        }

        override fun loadAvatar(url: String) = with(vb) {
            imageLoader.loadInto(url, vb.ivAvatar)
        }

        override fun loadRepos(repo: GithubRepositoryRepo) {
            TODO("Not yet implemented")
        }
    }
}