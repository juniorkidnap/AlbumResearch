package com.whities.albumresearch.framework.presentation.album

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.whities.albumresearch.R
import com.whities.albumresearch.business.domain.models.Track
import com.whities.albumresearch.business.domain.state.DataState
import com.whities.albumresearch.business.navigation.AppNavigation
import com.whities.albumresearch.databinding.FragmentAlbumBinding
import com.whities.albumresearch.framework.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumFragment : Fragment() {

    @Inject
    lateinit var navigation: AppNavigation

    private lateinit var binding: FragmentAlbumBinding
    private lateinit var listAdapter: AlbumListAdapter
    private val viewModel by viewModels<AlbumViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
            .setInterpolator(LinearInterpolator())
        exitTransition = inflater.inflateTransition(R.transition.slide_left)
            .setInterpolator(LinearInterpolator())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        loadAlbumInfo()
        subscribeObservers()
        subscribeButtonsListeners()
        mainViewModel.album.value?.let { collection ->
            viewModel.getData(collection.collectionId)
        }
    }

    private fun initAdapter() {
        listAdapter = AlbumListAdapter().apply {
            binding.recyclerViewTrackList.adapter = this
        }
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success<List<Track>> -> {
                    displayResult(dataState.data)
                    displayProgressBar(false)
                    displayError(false)
                }
                is DataState.Error -> {
                    displayError(true)
                    displayProgressBar(false)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                    displayError(false)
                }
                is DataState.NotFound -> {
                    displayError(true)
                }
            }
        })
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.albumProgressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun displayError(isDisplayed: Boolean) {
        binding.textErrorAlbum.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun displayResult(data: List<Track>) {
        listAdapter.submitList(data)
        binding.albumInfo.text = viewModel.calculateDuration(data)
    }

    private fun loadAlbumInfo() {
        mainViewModel.album.value?.apply {
            setAlbumArtwork(artworkUrl100)
            binding.apply {
                albumTitile.text = collectionName
                val artistNameText = "by $artistName"
                albumArtist.text = artistNameText
                val publishYearText = "$collectionType • $releaseDate"
                albumPublishYear.text = publishYearText
            }
        }
    }

    private fun setAlbumArtwork(artworkUrl: String) {
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)
        Glide.with(this).load(artworkUrl).apply(options).into(binding.albumArtwork)
    }

    private fun subscribeButtonsListeners() {
        binding.buttonBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}