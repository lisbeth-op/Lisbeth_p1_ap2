@file:OptIn(ExperimentalMaterial3Api::class)

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.room.Delete
import com.example.lisbeth_p1_ap2.data.local.entities.DivisionEntity
import com.example.lisbeth_p1_ap2.ui.componentes.CampodeTexto
import com.example.lisbeth_p1_ap2.ui.theme.Division.DivisionViewModel


@Composable
fun DivisionScreen(divisionViewModel: DivisionViewModel = hiltViewModel()) {
    Column(Modifier.padding(8.dp)) {
        RegistroDivion()
        Divider(Modifier.padding(vertical = 5.dp))
        HistorialScreen()
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
                    //Spacer(Modifier.width(16.dp))

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
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.height(80.dp)) {
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
    val contexto = LocalContext.current
    Button(
        onClick =
    {
        if (divisionViewModel.saveDivision()) {
            Toast.makeText(contexto, "Guardado Correctamente", Toast.LENGTH_SHORT).show()
        }
   }, Modifier.fillMaxWidth()
    )
    {
        Icon(imageVector = Icons.Default.Calculate, contentDescription = null)
        Text(text = "Guardar", Modifier.padding(start = 8.dp))
    }

}

@Composable
fun HistorialScreen(divisionViewModel: DivisionViewModel = hiltViewModel()) {
    val uiState = divisionViewModel.listUIState.collectAsState()
    Row {
        Text(text = "Historial de Resultado",style = MaterialTheme.typography.titleLarge)
        Icon(imageVector = Icons.Default.Timelapse, contentDescription =null )

    }

    LazyColumn() {
        uiState.value.lista.forEach {
            item {
                DivisionEntityRow(it) {
                    divisionViewModel.delete(it)
                }
            }
        }
    }
}

@Composable
fun DivisionEntityRow(divisionEntity: DivisionEntity, onDelete: () -> Unit) {

    Text(divisionEntity.nombre, style = MaterialTheme.typography.titleMedium)
    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
        Column {
            Text("Dividendo: " +divisionEntity.dividendo.toString())
            Text("Cociente: " +divisionEntity.cociente.toString())

        }
        Column {
            Text("Divisor: " +divisionEntity.divisor.toString())
            Text("Residuo: " +divisionEntity.residuo.toString())

        }
        IconButton(onClick =onDelete )

        {
                        Icon(imageVector = Icons.Default.Close, contentDescription =null, tint = Color.Red,
                            modifier = Modifier.
                            fillMaxWidth()
                            .aspectRatio(1f)
                            .border(1.dp, Color.Red)
                            .size(10.dp)
                            .padding(10.dp))

        }
    }
    Divider()


}