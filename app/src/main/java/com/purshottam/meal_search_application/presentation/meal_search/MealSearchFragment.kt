package com.purshottam.meal_search_application.presentation.meal_search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.purshottam.meal_search_application.databinding.FragmentMealSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MealSearchFragment : Fragment() {

    private var _binding: FragmentMealSearchBinding? = null
    val binding: FragmentMealSearchBinding
        get() = _binding!!

    private val mealSearchViewModel: MealSearchViewModel by viewModels()
    private val mealSearchAdapter = MealSearchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealSearchBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.svMeal.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mealSearchViewModel.searchMealList(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        binding.rvMealList.apply {
            adapter = mealSearchAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            mealSearchViewModel.mealSearchStateFlow.collect {
                if (it.isLoading) {
                    binding.pbMealSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.pbMealSearch.visibility = View.GONE
                }
                it.data?.let {
                    binding.pbMealSearch.visibility = View.GONE
                    mealSearchAdapter.setMealList(it.toMutableList())
                }
            }
        }

        mealSearchAdapter.mealClickListener {
            findNavController().navigate(
                MealSearchFragmentDirections.actionMealSearchFragmentToMealDetailsFragment(
                    mealId = it.mealId
                )
            )
            Log.d("QWERTY", "onViewCreated: mealId --> ${it.mealId}")
        }
    }
}