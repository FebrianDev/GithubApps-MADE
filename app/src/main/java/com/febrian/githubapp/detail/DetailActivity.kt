package com.febrian.githubapp.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.febrian.core.core.domain.model.Github
import com.febrian.core.core.utils.DataMapper
import com.febrian.githubapp.R
import com.febrian.githubapp.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailGithub = intent.getParcelableExtra<Github>(EXTRA_DATA)
        showDetailGithub(detailGithub?.username.toString())
        setFavorite(detailGithub)

        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showDetailGithub(detailGithub: String) {
        detailViewModel.detailUsers(detailGithub).observe(this, {
            if (it != null) {
                when (it) {
                    is com.febrian.core.core.data.Resource.Loading -> {
                        binding.progressBar.visibility =
                            View.VISIBLE
                    }
                    is com.febrian.core.core.data.Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        showData(DataMapper.mapEntityToDomain(DataMapper.mapResponseToEntity(it.data!!)))
                    }
                    is com.febrian.core.core.data.Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun showData(data: Github?) {
        if (data != null) {
            binding.name.text = data.name.toString()
            binding.username.text = data.username.toString()
            binding.organization.text = data.company.toString()
            binding.city.text = data.location.toString()
            binding.follower.text = data.follower.toString()
            binding.following.text = data.following.toString()
            val repositories = resources.getString(R.string.repositories)
            binding.repo.text = "${data.repository} $repositories"
            Glide.with(applicationContext).load(data.avatar.toString())
                .error(R.drawable.ic_baseline_broken_image_24).into(binding.img)
        }
    }

    private fun setFavorite(data: Github?) {
        if (data != null) {
            var statusFavorite = data.isFavorite
            setStatusFavorite(statusFavorite)
            binding.btnFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteGithub(data, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnFavorite.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
            binding.btnFavorite.text = resources.getString(R.string.remove_from_favorite)
            binding.btnFavorite.setTextColor(ContextCompat.getColor(this, R.color.white))
        } else {
            binding.btnFavorite.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            binding.btnFavorite.text = resources.getString(R.string.add_to_favorite)
            binding.btnFavorite.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}