package com.example.sombras.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sombras.data.model.CharacterResponse
import com.example.sombras.data.model.UpdateCharacterRequest
import com.example.sombras.data.repository.PersonajesRepository
import com.example.sombras.utils.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditCharacterViewModel(
    private val repository: PersonajesRepository
) : ViewModel() {

    private val _personaje = MutableStateFlow<CharacterResponse?>(null)
    val personaje: StateFlow<CharacterResponse?> = _personaje

    fun loadCharacter(personajeId: Long) {
        viewModelScope.launch {
            try {
                val personajes = repository.getMisPersonajes(SessionManager.userId!!)
                _personaje.value = personajes.find { it.id == personajeId }
            } catch (e: Exception) {
                _personaje.value = null
            }
        }
    }

    fun updateCharacter(
        personajeId: Long,
        userId: Long,
        request: UpdateCharacterRequest,
        onSaved: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                repository.updateCharacter(personajeId, userId, request)
                onSaved()
            } catch (e: Exception) {
                // aquí luego puedes meter Snackbar / Toast
                e.printStackTrace()
            }
        }
    }
}
class EditCharacterViewModelFactory(
    private val repository: PersonajesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditCharacterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditCharacterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
