package com.whities.albumresearch.framework.presentation.search

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.whities.albumresearch.business.domain.models.Album
import com.whities.albumresearch.business.domain.state.DataState
import com.whities.albumresearch.business.navigation.AppNavigation
import com.whities.albumresearch.business.navigation.Screens
import com.whities.albumresearch.databinding.FragmentSearchBinding
import com.whities.albumresearch.framework.datasource.util.ALBUM_KEY
import com.whities.albumresearch.framework.datasource.util.ALBUM_RESULT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    @Inject
    lateinit var navigation: AppNavigation

    private lateinit var binding: FragmentSearchBinding
    private lateinit var listAdapter: SearchListAdapter
    private val viewModel by viewModels<SearchViewModel>()

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
        setFragmentResult(
            ALBUM_RESULT,
            bundleOf(ALBUM_KEY to album.collectionId)
        )
        navigation.navigateTo(Screens.AlbumScreen)
    }

    private fun subscribeObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dataState.collect { dataState ->
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
                        else -> {}
                    }
                }
            }
        }
    }

    private fun subscribeButtonsListener() {
        onEndIconClickListener()
        onKeyboardButtonClickListener()
    }

    private fun onKeyboardButtonClickListener() = with(binding) {
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

    private fun onEndIconClickListener() = with(binding) {
        textLayout.setEndIconOnClickListener {
            viewModel.getData(userInputLayout.text.toString())
            hideKeyboard()
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
