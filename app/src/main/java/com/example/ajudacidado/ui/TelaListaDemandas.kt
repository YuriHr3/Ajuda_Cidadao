package com.example.ajudacidado.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ajudacidado.domain.model.Categoria
import com.example.ajudacidado.domain.model.Demanda
import com.example.ajudacidado.domain.model.StatusDemanda

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaListaDemandas(
    demandas: List<Demanda>,
    onVoltar: () -> Unit = {},
    onSelecionarDemanda: (Demanda) -> Unit
) {

    var busca by remember { mutableStateOf("") }
    var categoriaSelecionada by remember {
        mutableStateOf<Categoria?>(null)
    }
    var statusSelecionado by remember {
        mutableStateOf<StatusDemanda?>(null)
    }

    var categoriaExpandida by remember {
        mutableStateOf(false)
    }
    var statusExpandido by remember {
        mutableStateOf(false)
    }

    val demandasFiltradas = demandas.filter { demanda ->

        val correspondeBusca = demanda.titulo.contains(
            busca,
            ignoreCase = true
        )

        val correspondeCategoria =
            categoriaSelecionada == null ||
                    demanda.categoria == categoriaSelecionada

        val correspondeStatus =
            statusSelecionado == null ||
                    demanda.status == statusSelecionado

        correspondeBusca &&
                correspondeCategoria &&
                correspondeStatus
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Demandas registradas")
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            OutlinedTextField(
                value = busca,
                onValueChange = { busca = it },
                label = { Text("Buscar por título") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Column {

                Button(
                    onClick = {
                        categoriaExpandida = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        categoriaSelecionada?.name
                            ?.replace("_", " ")
                            ?: "Todas as categorias"
                    )
                }

                DropdownMenu(
                    expanded = categoriaExpandida,
                    onDismissRequest = {
                        categoriaExpandida = false
                    }
                ) {

                    DropdownMenuItem(
                        text = { Text("Todas as categorias") },
                        onClick = {
                            categoriaSelecionada = null
                            categoriaExpandida = false
                        }
                    )

                    Categoria.entries.forEach { categoria ->

                        DropdownMenuItem(
                            text = {
                                Text(
                                    categoria.name.replace(
                                        "_",
                                        " "
                                    )
                                )
                            },
                            onClick = {
                                categoriaSelecionada = categoria
                                categoriaExpandida = false
                            }
                        )
                    }
                }
            }

            Column {

                Button(
                    onClick = {
                        statusExpandido = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        statusSelecionado?.name
                            ?.replace("_", " ")
                            ?: "Todos os status"
                    )
                }

                DropdownMenu(
                    expanded = statusExpandido,
                    onDismissRequest = {
                        statusExpandido = false
                    }
                ) {

                    DropdownMenuItem(
                        text = { Text("Todos os status") },
                        onClick = {
                            statusSelecionado = null
                            statusExpandido = false
                        }
                    )

                    StatusDemanda.entries.forEach { status ->

                        DropdownMenuItem(
                            text = {
                                Text(
                                    status.name.replace(
                                        "_",
                                        " "
                                    )
                                )
                            },
                            onClick = {
                                statusSelecionado = status
                                statusExpandido = false
                            }
                        )
                    }
                }
            }

            if (demandasFiltradas.isEmpty()) {

                Text(
                    if (demandas.isEmpty()) {
                        "Nenhuma demanda cadastrada."
                    } else {
                        "Nenhuma demanda encontrada com esses filtros."
                    }
                )

            } else {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        bottom = 16.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(
                        items = demandasFiltradas,
                        key = { it.id }
                    ) { demanda ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onSelecionarDemanda(demanda)
                                },
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 2.dp
                            )
                        ) {

                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                Text(demanda.titulo)

                                Text(demanda.descricao)

                                Text(
                                    "Categoria: ${
                                        demanda.categoria.name.replace(
                                            "_",
                                            " "
                                        )
                                    }"
                                )

                                Text(
                                    "Prioridade: ${
                                        demanda.prioridade.name.replace(
                                            "_",
                                            " "
                                        )
                                    }"
                                )

                                Text(
                                    "Status: ${
                                        demanda.status.name.replace(
                                            "_",
                                            " "
                                        )
                                    }"
                                )

                                Text(
                                    "Localização: ${
                                        demanda.localizacao
                                            ?: "Não informada"
                                    }"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}