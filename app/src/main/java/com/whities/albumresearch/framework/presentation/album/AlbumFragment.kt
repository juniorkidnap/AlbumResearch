package com.whities.albumresearch.framework.presentation.album

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.whities.albumresearch.R
import com.whities.albumresearch.business.domain.models.Track
import com.whities.albumresearch.business.domain.state.DataState
import com.whities.albumresearch.business.navigation.AppNavigation
import com.whities.albumresearch.databinding.FragmentAlbumBinding
import com.whities.albumresearch.framework.datasource.util.ALBUM_KEY
import com.whities.albumresearch.framework.datasource.util.ALBUM_RESULT
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
        subscribeObservers()
        subscribeButtonsListeners()
        subscribeFragmentResultListener()
    }

    private fun subscribeFragmentResultListener() {
        setFragmentResultListener(ALBUM_RESULT) { _, result ->
            viewModel.getData(result.getLong(ALBUM_KEY))
        }
    }

    private fun initAdapter() {
        listAdapter = AlbumListAdapter().also {
            binding.recyclerViewTrackList.adapter = it
        }
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success<List<Track>> -> {
                    displayResult(dataState.data)
                    displayProgressBar(false)
                }
                is DataState.Error -> {
                    displayError(dataState.error)
                    displayProgressBar(false)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
                else -> {}
            }
        })
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.albumProgressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun displayError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    private fun displayResult(data: List<Track>?) {
        data?.get(0)?.apply {
            setAlbumArtwork(artworkUrl100)
            binding.apply {
                albumTitile.text = collectionName
                val artistNameText = "by $artistName"
                albumArtist.text = artistNameText
                val publishYearText = "Album • $releaseDate"
                albumPublishYear.text = publishYearText
            }
        }
        data?.let {
            listAdapter.submitList(it)
            binding.albumInfo.text = viewModel.calculateDuration(it)
        }
    }

    private fun setAlbumArtwork(artworkUrl: String?) {
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
