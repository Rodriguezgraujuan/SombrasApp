package com.example.sombras.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sombras.data.model.CharacterResponse
import com.example.sombras.data.repository.PersonajesRepository
import com.example.sombras.ui.screens.CharacterFilter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val repository: PersonajesRepository
) : ViewModel() {

    private val _selectedFilter = MutableStateFlow(CharacterFilter.PUBLIC)
    val selectedFilter = _selectedFilter.asStateFlow()

    private val _characters = MutableStateFlow<List<CharacterResponse>>(emptyList())
    val characters = _characters.asStateFlow()

    init {
        loadPublic()
    }

    fun selectPublic() {
        _selectedFilter.value = CharacterFilter.PUBLIC
        loadPublic()
    }

    fun selectMy(userId: Long) {
        _selectedFilter.value = CharacterFilter.MY
        loadMy(userId)
    }

    private fun loadPublic() {
        viewModelScope.launch {
            _characters.value = repository.getPublicos()
        }
    }

    private fun loadMy(userId: Long) {
        viewModelScope.launch {
            _characters.value = repository.getMios(userId)
        }
    }
}

class CharactersViewModelFactory(
    private val repository: PersonajesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(repository) as T
    }
}

