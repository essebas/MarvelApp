package com.zebas2.marvelapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zebas2.marvelapp.data.model.Character
import com.zebas2.marvelapp.data.model.Url
import com.zebas2.marvelapp.data.util.Prefers
import com.zebas2.marvelapp.data.util.Resource
import com.zebas2.marvelapp.databinding.FragmentCharactersBinding
import com.zebas2.marvelapp.presentation.adapter.CharacterAdapter
import com.zebas2.marvelapp.presentation.viewmodel.CharactersViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CharactersFragment : Fragment() {


    private lateinit var viewModel: CharactersViewModel
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var prefers: Prefers
    private lateinit var binding: FragmentCharactersBinding

    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var isSearch = false

    private var oldSearch = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharactersBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        characterAdapter = (activity as MainActivity).characterAdapter
        prefers = (activity as MainActivity).preferences

        characterAdapter.setOnItemCLickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_character", it)
            }

            var actionNav = R.id.action_charactersFragment_to_characterDetailFragment
            if (it.urls?.any { url -> url.type == "detail" } == true) {
                actionNav = R.id.action_charactersFragment_to_characterDetailWebViewFragment
            }

            findNavController().navigate(
                actionNav,
                bundle
            )
        }
        initRecyclerView()
        //Observers Objects
        viewCharactersList()
        viewSearchedCharacters()
        //Observers Objects
        setSearchView()
    }

    private fun viewCharactersList() {
        if (!isSearch) {
            viewModel.getCharacters(false)
        }
        viewModel.characters.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    setProgressBarVisibility(View.INVISIBLE)
                    response.data?.let {
                        characterAdapter.submitList(it.data.results)
                    }
                }
                is Resource.Error -> {
                    setProgressBarVisibility(View.INVISIBLE)
                    response.message?.let {
                        Toast.makeText(activity, "$it", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    setProgressBarVisibility(View.VISIBLE)
                }
            }
        })
    }

    private fun initRecyclerView() {
        binding.recyclerCharacters.apply {
            adapter = characterAdapter
            layoutManager = GridLayoutManager(activity, 3)
            addOnScrollListener(this@CharactersFragment.onScrollListener)
        }
    }

    private fun setProgressBarVisibility(visibility: Int) {
        isLoading = visibility == View.VISIBLE
        binding.progressBar.visibility = visibility
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.recyclerCharacters.layoutManager as GridLayoutManager
            val sizeOfTheCurrentLust = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstCompletelyVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentLust
            val shouldPaginate =
                !isLoading && !isLastPage && hasReachedToEnd && isScrolling && !isSearch
            if (shouldPaginate) {
                viewModel.getCharacters(true)
                isScrolling = false
            }
        }
    }

    private var tempSearch = ""

    private fun setSearchView() {

        binding.searchViewCharacters.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                setProgressBarVisibility(View.VISIBLE)
                isSearch = true
                viewModel.getSearchCharacters(query.toString(), false)
                oldSearch = query.toString()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                tempSearch = newText.toString()
                /*setProgressBarVisibility(View.VISIBLE)
                isSearch = true
                MainScope().launch {
                    delay(3000)
                    if (tempSearch == newText.toString()) {
                        viewModel.getSearchCharacters(tempSearch, true)
                    } else {
                        if (tempSearch == "") {
                            viewModel.getCharacters(false)
                            this.cancel()
                        } else {
                            this.cancel()
                        }
                    }
                }*/
                return false
            }

        })

        binding.searchViewCharacters.setOnCloseListener {
            isSearch = false
            viewModel.getCharacters(false)
            false
        }
    }

    private val requestSearch = MainScope().launch {

    }

    private fun viewSearchedCharacters() {
        viewModel.searchedCharacters.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    setProgressBarVisibility(View.INVISIBLE)
                    response.data?.let {
                        characterAdapter.submitList(it.data.results)
                    }
                }
                is Resource.Error -> {
                    setProgressBarVisibility(View.INVISIBLE)
                    response.message?.let {
                        Toast.makeText(activity, "$it", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    setProgressBarVisibility(View.VISIBLE)
                }
            }
        })
    }

}