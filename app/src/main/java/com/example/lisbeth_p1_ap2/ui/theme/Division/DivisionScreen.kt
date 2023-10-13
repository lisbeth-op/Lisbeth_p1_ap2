@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lisbeth_p1_ap2.ui.theme.Division.DivisionViewModel


@Composable
fun DivisionScreen(divisionViewModel: DivisionViewModel = hiltViewModel()) {
    Column(Modifier.padding(8.dp)) {
            RegistroDivion()
    }
}

@Composable
fun RegistroDivion(divisionViewModel: DivisionViewModel = hiltViewModel()) {
    OutlinedTextField(
        value = divisionViewModel.Nombre,
        onValueChange = { divisionViewModel.onNombreChanged(it) },
        Modifier.fillMaxWidth(),
        label = { Text(text = "Nombres")}
        )
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        OutlinedTextField(
                value = divisionViewModel.Dividendo.toString(),
                onValueChange ={ divisionViewModel.onDividendoChanged(it) },
                Modifier.weight(1f),
                label = { Text(text = "Dividendo")}
        )
        OutlinedTextField(
            value = divisionViewModel.Divisor.toString(),
            onValueChange ={ divisionViewModel.onDivisorChanged(it) },
            Modifier.weight(1f),
            label = { Text(text = "Divisor")}


        )
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        OutlinedTextField(
            value = divisionViewModel.Cociente.toString(),
            onValueChange ={ divisionViewModel.onCocienteChanged(it) },
            Modifier.weight(1f),
            label = { Text(text = "Cociente")}


        )
        OutlinedTextField(
            value = divisionViewModel.Residuo.toString(),
            onValueChange ={ divisionViewModel.onResiduoChanged(it) },
            Modifier.weight(1f),
            label = { Text(text = "Residuo")}


        )
    }
    Button(onClick = { divisionViewModel.saveDivision() }) {
        Icon(imageVector = Icons.Default.Calculate, contentDescription = null )
        Text(text = "Guardar", Modifier.padding(start = 8.dp))
    }

}