
package com.example.ajudacidado.ui

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun VozCidadaNavigationBar(
    telaSelecionada: Int,
    onSelecionarTela: (Int) -> Unit
) {
    NavigationBar {

        NavigationBarItem(
            selected = telaSelecionada == 0,
            onClick = {
                onSelecionarTela(0)
            },
            icon = {},
            label = {
                Text("Cadastrar")
            }
        )

        NavigationBarItem(
            selected = telaSelecionada == 1,
            onClick = {
                onSelecionarTela(1)
            },
            icon = {},
            label = {
                Text("Demandas")
            }
        )
    }
}
