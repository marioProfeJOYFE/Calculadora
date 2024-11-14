package com.mrh.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrh.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val lista: List<String> = listOf("0","1", "2", "3", "4", "5", "6", "7", "8", "9")
    val operadores: List<String> = listOf("+", "-", "*", "/")
    var primerNumero by remember { mutableStateOf(true) }
    var texto by remember { mutableStateOf("") }
    var texto2 by remember { mutableStateOf("") }
    var operador by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    Column(modifier = modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(250, 197, 188, 255)
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ){
                Text(" $texto $operador $texto2", fontSize = 100.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(resultado, fontSize = 50.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }

        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            items(lista.reversed()) { valor ->
                FilledTonalButton(
                    onClick = { if (primerNumero) texto += valor else texto2 += valor },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(valor, fontSize = 30.sp)
                }
            }
            item{
                FilledTonalButton(
                    onClick = {
                        texto = ""
                        texto2 = ""
                        operador = ""
                        resultado = ""
                        primerNumero = true
                    },
                    modifier = Modifier.padding(5.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = Color(190, 54, 31, 255),
                        contentColor = Color.Black
                    )
                ) {
                    Text("C", fontSize = 30.sp)
                }
            }
            item{
                FilledTonalButton(
                    onClick = {
                        resultado = operacion(operador, texto, texto2)
                    },
                    modifier = Modifier.padding(5.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = Color(205, 220, 57, 255),
                        contentColor = Color.Black
                    )
                ) {
                    Text("=", fontSize = 30.sp)
                }
            }
            items(operadores) { valor ->
                FilledTonalButton(
                    onClick = {
                        if(resultado.isEmpty()) {
                            primerNumero = false
                            operador = valor
                        }else{
                            texto = resultado
                            texto2 = ""
                            operador = valor
                            resultado = ""
                        }
                    },
                    modifier = Modifier.padding(5.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = Color(250, 197, 188, 255),
                        contentColor = Color.Black
                    )
                ) {
                    Text(valor, fontSize = 30.sp)
                }
            }
        }

    }
}


fun operacion(operador: String, numero1: String, numero2: String) : String {
    when(operador){
        "+" -> return (numero1.toDouble() + numero2.toDouble()).toString()
        "-" -> return (numero1.toDouble() - numero2.toDouble()).toString()
        "*" -> return (numero1.toDouble() * numero2.toDouble()).toString()
        "/" -> return (numero1.toDouble() / numero2.toDouble()).toString()

    }
    return ""
}