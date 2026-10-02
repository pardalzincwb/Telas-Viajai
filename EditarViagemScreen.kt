package com.example.viajai_maf

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.viajai_maf.ui.theme.Viajai_MAFTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarViagemScreen(
    origemInicial: String = "CWB, PR",
    destinoInicial: String = "SP, SP",
    dataInicial: String = "02/09/2026",
    onVoltar: () -> Unit,
    onSalvar: (String, String, String) -> Unit
) {
    var origem by remember { mutableStateOf(origemInicial) }
    var destino by remember { mutableStateOf(destinoInicial) }
    var data by remember { mutableStateOf(dataInicial) }

    val orangeGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFE87735), Color(0xFF8B2500))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Viagem", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE87735))
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(orangeGradient)
                .padding(padding)
                .padding(24.dp)
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Alterar Informações",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF8B2500)
                    )

                    OutlinedTextField(
                        value = origem,
                        onValueChange = { origem = it },
                        label = { Text("Origem (De:)") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = destino,
                        onValueChange = { destino = it },
                        label = { Text("Destino (Para:)") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = data,
                        onValueChange = { data = it },
                        label = { Text("Data da Viagem") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = { onSalvar(origem, destino, data) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE87735)),
                        shape = RoundedCornerShape(25.dp)
                    ) {
                        Text("Salvar Alterações", fontSize = 16.sp, color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditarViagemScreenPreview() {
    Viajai_MAFTheme {
        EditarViagemScreen(
            onVoltar = {},
            onSalvar = { _, _, _ -> }
        )
    }
}
