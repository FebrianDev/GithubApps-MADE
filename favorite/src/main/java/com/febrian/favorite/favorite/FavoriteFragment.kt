package com.febrian.favorite.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.febrian.core.core.domain.model.Github
import com.febrian.core.core.ui.GithubAdapter
import com.febrian.favorite.databinding.FavoriteFragmentBinding
import com.febrian.githubapp.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoriteFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(module)

        if (activity != null) {

            val githubAdapter = GithubAdapter()
            githubAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, it)
                startActivity(intent)
            }

            favoriteViewModel.favorite.observe(viewLifecycleOwner) { favorite ->
                githubAdapter.setData(favorite as ArrayList<Github>)
                with(binding.rvGithub) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = githubAdapter
                }
                binding.viewEmpty.root.visibility =
                    if (favorite.isNotEmpty()) View.GONE else View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}