package com.example.lesson1_mvp.view.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1_mvp.databinding.ItemRepositoryBinding
import com.example.lesson1_mvp.model.GithubRepositoryRepo
import com.example.lesson1_mvp.presentation.IUserListPresenter
import com.example.lesson1_mvp.view.UserItemView

class ReposRVAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<ReposRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }
    }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    class ViewHolder(private val vb: ItemRepositoryBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {

        override var pos: Int = -1

        override fun showLogin(login: String) {
        }

        override fun loadAvatar(url: String) = with(vb) {
        }

        override fun loadRepos(repo: GithubRepositoryRepo) {
            vb.tvRepository.text = repo.name
        }
    }
}