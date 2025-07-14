package com.example.optune.viewmodel.company

import androidx.lifecycle.ViewModel
import com.example.optune.data.model.Offer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CompanyViewModel : ViewModel() {
    private val _opportunities = MutableStateFlow<List<Offer>>(emptyList())
    val opportunities: StateFlow<List<Offer>> = _opportunities

    fun postOpportunity(offer: Offer) {
        // TODO: Implement API call to post opportunity
    }
}