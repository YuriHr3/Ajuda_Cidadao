package com.example.ajudacidado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.ajudacidado.data.local.DatabaseProvider
import com.example.ajudacidado.data.repository.DemandaRepositoryImpl
import com.example.ajudacidado.ui.DemandaViewModel
import com.example.ajudacidado.ui.DemandaViewModelFactory
import com.example.ajudacidado.ui.theme.AjudaCidadãoTheme

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
                TelaInicial(viewModel)
            }
        }
    }
}

@Composable
fun TelaInicial(viewModel: DemandaViewModel) {

    var quantidadeDemandas by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(Unit) {
        viewModel.listarDemandas { demandas ->
            quantidadeDemandas = demandas.size
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Text(
            text = "Voz Cidadã\n\nDemandas cadastradas: $quantidadeDemandas",
            modifier = Modifier.padding(innerPadding)
        )
    }
}