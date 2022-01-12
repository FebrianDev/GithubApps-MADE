package com.febrian.githubapp.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.febrian.core.core.data.Resource
import com.febrian.core.core.domain.model.Github
import com.febrian.core.core.ui.GithubAdapter
import com.febrian.githubapp.R
import com.febrian.githubapp.databinding.FragmentHomeBinding
import com.febrian.githubapp.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val githubAdapter = GithubAdapter()
            githubAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, it)
                startActivity(intent)
            }

            homeViewModel.github.observe(viewLifecycleOwner) { github ->
                if (github != null) {
                    when (github) {
                        is Resource.Loading<*> -> {
                            binding.progressBar.visibility =
                                View.VISIBLE
                        }
                        is Resource.Success<*> -> {
                            binding.progressBar.visibility = View.GONE
                            githubAdapter.setData(github.data as ArrayList<Github>)
                            with(binding.rvGithub) {
                                layoutManager = LinearLayoutManager(context)
                                setHasFixedSize(true)
                                adapter = githubAdapter
                            }
                        }
                        is Resource.Error<*> -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                github.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}