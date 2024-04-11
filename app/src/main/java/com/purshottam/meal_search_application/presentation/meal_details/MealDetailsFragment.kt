package com.purshottam.meal_search_application.presentation.meal_details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.purshottam.meal_search_application.databinding.FragmentMealDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MealDetailsFragment : Fragment() {

    private var _binding: FragmentMealDetailsBinding? = null
    val binding: FragmentMealDetailsBinding
        get() = _binding!!

    private val args: MealDetailsFragmentArgs by navArgs()
    private val mealDetailsViewModel: MealDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.mealId.let {
            Log.d("QWERTY", "onViewCreated: mealIdReceived --> $it")
            mealDetailsViewModel.getMealDetails(it.toString())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            mealDetailsViewModel.mealDetailsStateFlow.collect { mealDetails ->
                if (mealDetails.isLoading) {

                }
                if (mealDetails.error.isNotBlank()) {
                    Toast.makeText(requireContext(), mealDetails.error, Toast.LENGTH_SHORT).show()
                }
                mealDetails.data.let {
                    binding.mealDetails = it
                }
            }
        }

        binding.detailsBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}