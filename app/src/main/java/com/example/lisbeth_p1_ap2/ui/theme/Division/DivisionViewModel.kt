package com.example.lisbeth_p1_ap2.ui.theme.Division

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lisbeth_p1_ap2.data.local.dao.DivisionDao
import com.example.lisbeth_p1_ap2.data.local.entities.DivisionEntity
import com.example.lisbeth_p1_ap2.data.repository.DivisionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DivisionViewModel @Inject constructor(
    private val repository: DivisionRepository):ViewModel() {
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

    var nombreLabel by mutableStateOf("")
    var dividendoLabel by mutableStateOf("Dividiendo Requerido")
    var divisorLabel by mutableStateOf("")
    var cocienteLabel by mutableStateOf("")
    var residuoLabel by mutableStateOf("")

    private val _isMessageShown = MutableSharedFlow<Boolean>()
    val isMessageShownFlow = _isMessageShown.asSharedFlow()

    fun setMessageShown() {
        viewModelScope.launch {
            _isMessageShown.emit(true)
        }
    }

    fun onNombreChanged(valor: String) {
        Nombre = valor
        nombreError = valor.isBlank() || valor.length < 3
    }

    fun onDividendoChanged(valor: Int) {
        Dividendo = valor
        dividendoError = valor <= 0
        dividendoLabel = if (dividendoError) "El dividendo debe ser mayor que cero" else ""
    }

    fun onDivisorChanged(valor: Int) {
        Divisor = valor
        divisorError = valor <= 0
        divisorLabel = if (divisorError) "El divisor debe ser mayor que cero" else ""
    }


    fun onResiduoChanged(valor: Int) {
        Residuo = valor
        residuoError = valor != Dividendo % Divisor
        residuoLabel = if (residuoError) "El residuo proporcionado no es correcto" else ""
    }


    fun onCocienteChanged(valor: Int) {
        Cociente = valor
        cocienteError = valor != Dividendo / Divisor
        cocienteLabel = if (cocienteError) "El cociente proporcionado no es correcto" else ""
    }



    fun validarCampos(): Boolean {
        nombreError = Nombre.isBlank() || Nombre.length < 3
    return nombreError}

    private fun limpiar() {
        Nombre=" Escriba su nombre"
        Dividendo=0
        Divisor=0
        Cociente=0
        Residuo=0
    }

 fun saveDivision(){
     if (validarCampos()){
         viewModelScope.launch {
             val division= DivisionEntity(

                 nombre=Nombre,
                 divisor = Divisor,
                 dividendo = Dividendo,
                 cociente=Cociente,
                 residuo = Residuo
             )

         }

     }
 }

}


