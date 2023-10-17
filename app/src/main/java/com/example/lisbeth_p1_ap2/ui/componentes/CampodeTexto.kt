@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.lisbeth_p1_ap2.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CampodeTexto(
    valor:String,
    alCambiarValor: (String)->Unit,
    label: String,
    isError: Boolean=false,
    opciones:KeyboardOptions= KeyboardOptions.Default,
    mensajeError: String?=null,
    modifier: Modifier=Modifier
){
    Column(modifier.padding(bottom = if (isError){0.dp}else{10.dp})) {
        OutlinedTextField(
            value = valor,
            onValueChange =alCambiarValor,
            label = { Text(text = label)},
            keyboardOptions = opciones,
            modifier = modifier

        )
        if (isError && !mensajeError.isNullOrEmpty()){
            Text(
                text =mensajeError,
                color = Color.Red,
                modifier=Modifier.padding(start = 10.dp)
                )

        }
    }
}