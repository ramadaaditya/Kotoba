package com.ramstudio.kotoba.features.kana

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramstudio.kotoba.core.data.repository.KanaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KanaViewModel @Inject constructor(
    private val kanaRepository: KanaRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(KanaUiState())
    val uiState: StateFlow<KanaUiState> = _uiState.asStateFlow()

    private var collectionJob: Job? = null

    init {
        viewModelScope.launch {
            val existing = kanaRepository.getAllCharacters().first()
            if (existing.isEmpty()) {
                kanaRepository.seedInitialData(KanaSeedData.all())
            }
            refreshCharacters(_uiState.value.selectedType)
        }
    }

    fun onTypeSelected(type: String) {
        if (_uiState.value.selectedType == type) return
        _uiState.value = _uiState.value.copy(selectedType = type)
        refreshCharacters(type)
    }

    private fun refreshCharacters(type: String) {
        collectionJob?.cancel()
        collectionJob = viewModelScope.launch {
            kanaRepository.getCharactersByType(type).collect { characters ->
                _uiState.value = _uiState.value.copy(
                    selectedType = type,
                    items = characters,
                    isLoading = false,
                )
            }
        }
    }
}
