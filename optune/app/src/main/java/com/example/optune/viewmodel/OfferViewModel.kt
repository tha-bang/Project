package com.example.optune.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.optune.data.model.Offer
import com.example.optune.network.EofferApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class OfferViewModel @Inject constructor(
    private val erofferApi: EofferApiService
) : ViewModel() {

    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    // Make 'offers' open if FakeOfferViewModel needs to override its getter,
    // though in our current FakeOfferViewModel, we're providing a new backing field
    // and overriding the property itself, which is fine.
    open val offers: StateFlow<List<Offer>> = _offers

    init {
        fetchOffers()
    }

    open fun fetchOffers() { // <<<< MAKE THIS METHOD 'open'
        viewModelScope.launch {
            try {
                val fetchedOffersList = erofferApi.getOffers()
                _offers.value = fetchedOffersList
            } catch (e: Exception) {
                e.printStackTrace()
                _offers.value = emptyList()
            }
        }
    }
}
