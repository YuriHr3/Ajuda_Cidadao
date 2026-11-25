
package com.example.ajudacidado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.ajudacidado.data.local.DatabaseProvider
import com.example.ajudacidado.data.repository.DemandaRepositoryImpl
import com.example.ajudacidado.domain.model.Demanda
import com.example.ajudacidado.domain.model.Prioridade
import com.example.ajudacidado.domain.model.StatusDemanda
import com.example.ajudacidado.ui.DemandaViewModel
import com.example.ajudacidado.ui.DemandaViewModelFactory
import com.example.ajudacidado.ui.TelaCadastroDemanda
import com.example.ajudacidado.ui.TelaListaDemandas
import com.example.ajudacidado.ui.theme.AjudaCidadãoTheme
import java.util.UUID
import com.example.ajudacidado.ui.VozCidadaNavigationBar
import com.example.ajudacidado.ui.TelaDetalheDemanda

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = DatabaseProvider.getDatabase(this)

        val repository = DemandaRepositoryImpl(
            database.demandaDao()
        )

        val factory = DemandaViewModelFactory(repository)

        val viewModel = ViewModelProvider(
            this,
            factory
        )[DemandaViewModel::class.java]

        setContent {
            AjudaCidadãoTheme {
                AppVozCidada(viewModel)
            }
        }
    }
}


@Composable
fun AppVozCidada(viewModel: DemandaViewModel) {

    var telaAtual by remember {
        mutableIntStateOf(0)
    }

    var demandas by remember {
        mutableStateOf<List<Demanda>>(emptyList())
    }

    var demandaSelecionada by remember {
        mutableStateOf<Demanda?>(null)
    }

    LaunchedEffect(telaAtual) {
        if (telaAtual == 1) {
            viewModel.listarDemandas { resultado ->
                demandas = resultado
            }
        }
    }

    Scaffold(
        bottomBar = {
            VozCidadaNavigationBar(
                telaSelecionada = if (telaAtual == 2) 1 else telaAtual,
                onSelecionarTela = { novaTela ->
                    demandaSelecionada = null
                    telaAtual = novaTela
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            when (telaAtual) {

                0 -> {
                    TelaCadastroDemanda(
                        onSalvar = {
                                titulo, descricao, categoria, localizacao, onResultado ->

                            val demanda = Demanda(
                                id = UUID.randomUUID().toString(),
                                titulo = titulo,
                                descricao = descricao,
                                categoria = categoria,
                                prioridade = Prioridade.MEDIA,
                                status = StatusDemanda.RECEBIDA,
                                localizacao = localizacao.ifBlank { null },
                                dataCriacao = System.currentTimeMillis()
                            )

                            viewModel.registrarDemanda(demanda) { sucesso ->
                                onResultado(sucesso)
                            }
                        }
                    )
                }

                1 -> {
                    TelaListaDemandas(
                        demandas = demandas,
                        onSelecionarDemanda = { demanda ->
                            demandaSelecionada = demanda
                            telaAtual = 2
                        }
                    )
                }

                2 -> {
                    demandaSelecionada?.let { demanda ->
                        TelaDetalheDemanda(
                            demanda = demanda,
                            onVoltar = {
                                telaAtual = 1
                            }
                        )
                    }
                }
            }
        }
    }
}