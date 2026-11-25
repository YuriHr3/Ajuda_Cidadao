package com.example.ajudacidado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.ajudacidado.data.local.DatabaseProvider
import com.example.ajudacidado.data.repository.DemandaRepositoryImpl
import com.example.ajudacidado.domain.model.Demanda
import com.example.ajudacidado.domain.model.Prioridade
import com.example.ajudacidado.domain.model.StatusDemanda
import com.example.ajudacidado.ui.DemandaViewModel
import com.example.ajudacidado.ui.DemandaViewModelFactory
import com.example.ajudacidado.ui.TelaCadastroDemanda
import com.example.ajudacidado.ui.theme.AjudaCidadãoTheme
import java.util.UUID

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
                TelaCadastroDemanda(
                    onSalvar = { titulo, descricao, categoria, localizacao, onResultado ->

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
        }
    }
}
