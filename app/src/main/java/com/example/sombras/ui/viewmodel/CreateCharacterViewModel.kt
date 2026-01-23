package com.example.sombras.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sombras.data.model.Clase
import com.example.sombras.data.model.CreateCharacterRequest
import com.example.sombras.data.model.Raza
import com.example.sombras.data.repository.PersonajesRepository
import kotlinx.coroutines.launch

class CreateCharacterViewModel(
    private val repository: PersonajesRepository
) : ViewModel() {

    var clases by mutableStateOf<List<Clase>>(emptyList())
        private set

    var razas by mutableStateOf<List<Raza>>(emptyList())
        private set

    var loading by mutableStateOf(false)
        private set

    fun cargarDatos() {
        viewModelScope.launch {
            clases = repository.getClases()
            razas = repository.getRazas()
        }
    }

    fun crearPersonaje(
        request: CreateCharacterRequest,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                loading = true
                repository.crearPersonaje(request)
                onSuccess()
            } catch (e: Exception) {
                onError(e.message ?: "Error desconocido")
            } finally {
                loading = false
            }
        }
    }
}

class CreateCharacterViewModelFactory(
    private val repository: PersonajesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateCharacterViewModel(repository) as T
    }
}

