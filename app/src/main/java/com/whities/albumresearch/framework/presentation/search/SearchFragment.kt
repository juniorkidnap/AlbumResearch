package com.whities.albumresearch.framework.presentation.search

import android.app.Activity
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.whities.albumresearch.R
import com.whities.albumresearch.business.domain.models.Album
import com.whities.albumresearch.business.domain.state.DataState
import com.whities.albumresearch.business.navigation.AppNavigation
import com.whities.albumresearch.business.navigation.Screens
import com.whities.albumresearch.databinding.FragmentSearchBinding
import com.whities.albumresearch.framework.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    @Inject
    lateinit var navigation: AppNavigation

    private lateinit var binding: FragmentSearchBinding
    private lateinit var listAdapter: SearchListAdapter
    private val viewModel by viewModels<SearchViewModel>()
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
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        subscribeButtonsListener()
        subscribeObservers()
    }

    private fun initAdapter() {
        listAdapter = SearchListAdapter(context = requireContext()) { album ->
            adapterOnClick(album)
        }.also {
            binding.searchRecyclerView.adapter = it
        }
    }

    private fun adapterOnClick(album: Album) {
        mainViewModel.setAlbum(album = album)
        navigation.navigateTo(Screens.AlbumScreen)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success<List<Album>> -> {
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
            }
        })
    }

    private fun subscribeButtonsListener() {
        onEndIconClickListener()
        onKeyboardButtonClickListener()
    }

    private fun onKeyboardButtonClickListener() {
        binding.apply {
            userInputLayout.setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        viewModel.getData(userInputLayout.text.toString())
                        hideKeyboard()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun onEndIconClickListener() {
        binding.apply {
            textLayout.setEndIconOnClickListener {
                viewModel.getData(userInputLayout.text.toString())
                hideKeyboard()
            }
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.searchProgressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun displayError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    private fun displayResult(data: List<Album>) {
        listAdapter.submitList(data)
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = requireActivity().currentFocus
        if (view == null) {
            view = View(requireActivity())
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
