
package com.example.ajudacidado.ui

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ajudacidado.domain.model.Demanda
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaListaDemandas(
    demandas: List<Demanda>,
    onVoltar: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Demandas registradas")
                }
            )
        }
    ) { paddingValues ->

        if (demandas.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Nenhuma demanda cadastrada.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = demandas,
                    key = { it.id }
                ) { demanda ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
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
                                    demanda.categoria.name.replace("_", " ")
                                }"
                            )

                            Text(
                                "Prioridade: ${
                                    demanda.prioridade.name.replace("_", " ")
                                }"
                            )

                            Text(
                                "Status: ${
                                    demanda.status.name.replace("_", " ")
                                }"
                            )

                            Text(
                                "Localização: ${
                                    demanda.localizacao ?: "Não informada"
                                }"
                            )
                        }
                    }
                }
            }
        }
    }
}
