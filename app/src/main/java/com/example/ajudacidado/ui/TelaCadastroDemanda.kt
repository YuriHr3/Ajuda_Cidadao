
package com.example.ajudacidado.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ajudacidado.domain.model.Categoria

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaCadastroDemanda(
    onSalvar: (String, String, Categoria, String) -> Unit,
    onVoltar: () -> Unit = {}
) {
    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var localizacao by remember { mutableStateOf("") }

    var categoriaSelecionada by remember {
        mutableStateOf(Categoria.OUTROS)
    }

    var menuAberto by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Nova demanda")
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text("Registre um problema ou necessidade da comunidade.")

            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") },
                placeholder = { Text("Ex.: Buraco na rua") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = descricao,
                onValueChange = { descricao = it },
                label = { Text("Descrição") },
                placeholder = {
                    Text("Descreva o problema detalhadamente")
                },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4
            )

            Column {
                Button(
                    onClick = { menuAberto = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Categoria: ${
                            categoriaSelecionada.name
                                .replace("_", " ")
                        }"
                    )
                }

                DropdownMenu(
                    expanded = menuAberto,
                    onDismissRequest = {
                        menuAberto = false
                    }
                ) {
                    Categoria.entries.forEach { categoria ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    categoria.name.replace("_", " ")
                                )
                            },
                            onClick = {
                                categoriaSelecionada = categoria
                                menuAberto = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = localizacao,
                onValueChange = { localizacao = it },
                label = { Text("Localização") },
                placeholder = {
                    Text("Rua, bairro ou local")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Button(
                onClick = {
                    if (
                        titulo.isNotBlank() &&
                        descricao.isNotBlank()
                    ) {
                        onSalvar(
                            titulo.trim(),
                            descricao.trim(),
                            categoriaSelecionada,
                            localizacao.trim()
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = titulo.isNotBlank() &&
                        descricao.isNotBlank()
            ) {
                Text("Registrar demanda")
            }
        }
    }
}
