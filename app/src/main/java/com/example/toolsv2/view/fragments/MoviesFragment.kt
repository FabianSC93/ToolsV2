package com.example.toolsv2.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.toolsv2.*
import com.example.toolsv2.databinding.FragmentMoviesBinding
import com.example.toolsv2.model.Api.Movies
import com.example.toolsv2.model.resources.CheckNetwork
import com.example.toolsv2.present.MoviesPresentImpl
import com.example.toolsv2.present.MoviesPresenter
import com.example.toolsv2.view.adapters.MoviesAdapter
import com.example.toolsv2.view.interfaces.MoviesView


class MoviesFragment : Fragment(R.layout.fragment_movies), MoviesView {

    private lateinit var mBinding:FragmentMoviesBinding

    private var moviesPresenter: MoviesPresenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentMoviesBinding.bind(view)

        moviesPresenter = MoviesPresentImpl(this, CheckNetwork(requireActivity()).isOnline())
        getMovies()
    }

    override fun showMovies(movies: MutableList<Movies>) {
        activity?.runOnUiThread {
            mBinding.rvMovies.layoutManager = LinearLayoutManager(context)
            mBinding.rvMovies.adapter = MoviesAdapter(movies)
        }

    }

    override fun getMovies() {
        moviesPresenter?.getMovies()
    }
}