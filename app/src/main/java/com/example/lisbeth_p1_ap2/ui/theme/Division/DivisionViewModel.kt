package com.example.lisbeth_p1_ap2.ui.theme.Division

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lisbeth_p1_ap2.data.local.entities.DivisionEntity
import com.example.lisbeth_p1_ap2.data.repository.DivisionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class divisionListUIState(
    val lista: List<DivisionEntity> = emptyList()
)

@HiltViewModel
class DivisionViewModel @Inject constructor(
    private val repository: DivisionRepository
) : ViewModel() {
    var Nombre by mutableStateOf("")
    var Dividendo by mutableStateOf(0)
    var Divisor by mutableStateOf(0)
    var Cociente by mutableStateOf(0)
    var Residuo by mutableStateOf(0)

    var nombreError by mutableStateOf(false)
    var dividendoError by mutableStateOf(false)
    var divisorError by mutableStateOf(false)
    var cocienteError by mutableStateOf(false)
    var residuoError by mutableStateOf(false)

    private val _listUIState= MutableStateFlow(divisionListUIState())
    val listUIState=_listUIState.asStateFlow()
    private val _isMessageShown = MutableSharedFlow<Boolean>()
    val isMessageShownFlow = _isMessageShown.asSharedFlow()

    fun setMessageShown() {
        viewModelScope.launch {
            _isMessageShown.emit(true)
        }
    }

    fun onNombreChanged(valor: String) {
        Nombre = valor

    }


    fun onDividendoChanged(numero: String) {
        Dividendo = if (numero.isNullOrEmpty()) {
            0
        } else
            numero.toInt()

    }

    fun onDivisorChanged(numero: String) {
        Divisor = if (numero.isNullOrEmpty()) {
            0
        } else numero.toInt()


    }

    fun onCocienteChanged(numero: String) {
        Cociente = if (numero.isNullOrEmpty()) {
            0
        } else numero.toInt()

    }

    fun onResiduoChanged(numero: String) {
        Residuo =if (numero.isNullOrEmpty()) {
            0
        } else numero.toInt()


    }

    private fun limpiar() {
        Nombre = ""
        Dividendo = 0
        Divisor = 0
        Cociente = 0
        Residuo = 0
    }

    fun delete(dividir: DivisionEntity) {
  viewModelScope.launch {
            repository.delete(dividir)
        }
    }

    fun validarCampos(): Boolean {
        dividendoError = Dividendo==0
        if (Cociente != 0 && Dividendo != 0 && Divisor != 0) {
            cocienteError = Cociente != (Dividendo - Residuo) / Divisor

            residuoError = Residuo != Dividendo - (Cociente* Divisor)

            divisorError = (Dividendo - Residuo) / Cociente != Divisor



        }
        return !cocienteError && !residuoError && !dividendoError && !dividendoError && Nombre.isNotBlank() && Nombre.length > 3


    }

    fun saveDivision(): Boolean {
        if (validarCampos()) {
            viewModelScope.launch {
                val division = DivisionEntity(

                    nombre = Nombre,
                    divisor = Divisor,
                    dividendo = Dividendo,
                    cociente = Cociente,
                    residuo = Residuo
                )
                repository.save(division)

            }
            actualizarResultado()
            limpiar()
            return true
        }
        return  false
    }
    fun actualizarResultado(){
        viewModelScope.launch {
            _listUIState.update {
                it.copy(
                    lista = repository.getAll()
                )
            }
        }

    }
    init {
        actualizarResultado()
    }
}