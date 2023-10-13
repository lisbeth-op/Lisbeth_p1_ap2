@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lisbeth_p1_ap2.ui.componentes.CampodeTexto
import com.example.lisbeth_p1_ap2.ui.theme.Division.DivisionViewModel


@Composable
fun DivisionScreen(divisionViewModel: DivisionViewModel = hiltViewModel()) {
    Column(Modifier.padding(8.dp)) {
        RegistroDivion()
    }
}

@Composable
fun RegistroDivion(divisionViewModel: DivisionViewModel = hiltViewModel()) {
    CampodeTexto(
        valor = divisionViewModel.Nombre,
        alCambiarValor = { divisionViewModel.onNombreChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = "Nombres",
        mensajeError = "*Introduzca un nombre*",
        isError = divisionViewModel.nombreError
    )
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.height(80.dp)) {
        CampodeTexto(
            valor = divisionViewModel.Dividendo.toString(),
            alCambiarValor = { divisionViewModel.onDividendoChanged(it) },
            modifier = Modifier.weight(5f),
            label = "Dividendo",
            opciones = KeyboardOptions(keyboardType = KeyboardType.Number),
            mensajeError = "*El Dividendo es requerido*",
            isError = divisionViewModel.dividendoError


        )
        CampodeTexto(
            valor = divisionViewModel.Divisor.toString(),
            alCambiarValor = { divisionViewModel.onDivisorChanged(it) },
            modifier = Modifier.weight(5f),
            label = "Divisor",
            opciones = KeyboardOptions(keyboardType = KeyboardType.Number),
            mensajeError = "*El Divisor no es correcto*",
            isError = divisionViewModel.divisorError



        )
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.height(80.dp)){
        CampodeTexto(
            valor = divisionViewModel.Cociente.toString(),
            alCambiarValor = { divisionViewModel.onCocienteChanged(it) },
            modifier = Modifier.weight(1f),
            label = "Cociente",
            opciones = KeyboardOptions(keyboardType = KeyboardType.Number),
            mensajeError = "*El Cociente no es correcto*",
            isError = divisionViewModel.cocienteError


        )
        CampodeTexto(
            valor = divisionViewModel.Residuo.toString(),
            alCambiarValor = { divisionViewModel.onResiduoChanged(it) },
            modifier = Modifier.weight(1f),
            label = "Residuo",
            opciones = KeyboardOptions(keyboardType = KeyboardType.Number),
            mensajeError = "*El Residuo no es correcto*",
            isError = divisionViewModel.residuoError



        )
    }
    Button(onClick = { divisionViewModel.saveDivision() }) {
        Icon(imageVector = Icons.Default.Calculate, contentDescription = null)
        Text(text = "Guardar", Modifier.padding(start = 8.dp))
    }

}