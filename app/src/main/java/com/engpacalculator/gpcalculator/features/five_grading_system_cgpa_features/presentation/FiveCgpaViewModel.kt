package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.domain.repository.UniFiveSgpaResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FiveCgpaViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val myRepository: UniFiveSgpaResultRepository
) : ViewModel()