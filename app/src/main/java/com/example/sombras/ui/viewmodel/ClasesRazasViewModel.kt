import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sombras.data.model.Clase
import com.example.sombras.data.model.Raza
import com.example.sombras.data.repository.ClasesRazasRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClasesRazasViewModel(
    private val repository: ClasesRazasRepository
) : ViewModel() {

    private val _clases = MutableStateFlow<List<Clase>>(emptyList())
    val clases: StateFlow<List<Clase>> = _clases

    private val _razas = MutableStateFlow<List<Raza>>(emptyList())
    val razas: StateFlow<List<Raza>> = _razas

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadClases() {
        viewModelScope.launch {
            _isLoading.value = true
            _clases.value = repository.getClases()
            _isLoading.value = false
        }
    }

    fun loadRazas() {
        viewModelScope.launch {
            _isLoading.value = true
            _razas.value = repository.getRazas()
            _isLoading.value = false
        }
    }
}

class ClasesRazasViewModelFactory(
    private val repository: ClasesRazasRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClasesRazasViewModel::class.java)) {
            return ClasesRazasViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}

