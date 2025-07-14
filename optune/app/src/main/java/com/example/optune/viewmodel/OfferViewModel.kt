package com.example.optune.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.optune.data.model.Offer
import com.example.optune.data.network.EofferApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class OfferViewModel @Inject constructor(
    private val erofferApi: EofferApiService
) : ViewModel() {

    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    open val offers: StateFlow<List<Offer>> = _offers

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _locationFilter = MutableStateFlow("")
    val locationFilter: StateFlow<String> = _locationFilter

    private val _jobTypeFilter = MutableStateFlow("")
    val jobTypeFilter: StateFlow<String> = _jobTypeFilter

    private val _salaryRangeFilter = MutableStateFlow("")
    val salaryRangeFilter: StateFlow<String> = _salaryRangeFilter

    private val _experienceLevelFilter = MutableStateFlow("")
    val experienceLevelFilter: StateFlow<String> = _experienceLevelFilter

    private val _opportunityTypeFilter = MutableStateFlow("")
    val opportunityTypeFilter: StateFlow<String> = _opportunityTypeFilter

    val filteredOffers: StateFlow<List<Offer>> = combine(
        _offers,
        _searchQuery,
        _locationFilter,
        _jobTypeFilter,
        _salaryRangeFilter,
        _experienceLevelFilter,
        _opportunityTypeFilter
    ) { args ->
        @Suppress("UNCHECKED_CAST")
        val offersList = args[0] as List<Offer>
        val query = args[1] as String
        val location = args[2] as String
        val jobType = args[3] as String
        val salaryRange = args[4] as String
        val experienceLevel = args[5] as String
        val opportunityType = args[6] as String

        offersList.filter {
            (it.title.contains(query, ignoreCase = true) ||
            it.description.contains(query, ignoreCase = true)) &&
            (location.isEmpty() || it.location.equals(location, ignoreCase = true)) &&
            (jobType.isEmpty() || it.jobType.equals(jobType, ignoreCase = true)) &&
            (salaryRange.isEmpty() || it.salaryRange.equals(salaryRange, ignoreCase = true)) &&
            (experienceLevel.isEmpty() || it.experienceLevel.equals(experienceLevel, ignoreCase = true)) &&
            (opportunityType.isEmpty() || it.opportunityType.equals(opportunityType, ignoreCase = true))
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    init {
        fetchOffers()
    }

    open fun fetchOffers() {
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

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setLocationFilter(location: String) {
        _locationFilter.value = location
    }

    fun setJobTypeFilter(jobType: String) {
        _jobTypeFilter.value = jobType
    }

    fun setSalaryRangeFilter(salaryRange: String) {
        _salaryRangeFilter.value = salaryRange
    }

    fun setExperienceLevelFilter(experienceLevel: String) {
        _experienceLevelFilter.value = experienceLevel
    }

    fun setOpportunityTypeFilter(opportunityType: String) {
        _opportunityTypeFilter.value = opportunityType
    }
}