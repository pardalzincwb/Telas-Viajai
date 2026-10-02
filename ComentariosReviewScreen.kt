package com.example.viajai.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viajai_maf.ui.theme.Viajai_MAFTheme

data class Comentario(val autor: String, val texto: String, val nota: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComentariosReviewScreen(
    pousadaId: Int,
    onVoltar: () -> Unit
) {
    val comentarios = remember {
        mutableStateListOf(
            Comentario("Lucas M.", "Lugar excelente, ótimo atendimento!", 5),
            Comentario("Ana P.", "Muito limpo, café da manhã maravilhoso.", 5),
            Comentario("Carlos R.", "Boa localização, mas o Wi-Fi oscilou.", 4)
        )
    }

    var novoComentario by remember { mutableStateOf("") }
    var autorNome by remember { mutableStateOf("") }

    // Cálculo dinâmico extra pedido no requisito 3.2 do MAF
    val mediaAvaliacoes = if (comentarios.isNotEmpty()) {
        comentarios.map { it.nota }.average()
    } else 0.0

    val orangeGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFE87735), Color(0xFF8B2500))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Avaliações e Comentários", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE87735))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(orangeGradient)
                .padding(padding)
                .padding(16.dp)
        ) {
            // Header do Detalhe com Cálculo
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Média de Avaliações", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFB300), modifier = Modifier.size(32.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "%.1f / 5.0".format(mediaAvaliacoes),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF8B2500)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("(${comentarios.size} avaliações)", color = Color.Gray)
                    }
                }
            }

            // Formulário de Comentário
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Deixe sua avaliação", fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = autorNome,
                        onValueChange = { autorNome = it },
                        label = { Text("Seu Nome") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = novoComentario,
                        onValueChange = { novoComentario = it },
                        label = { Text("Comentário") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = {
                            if (autorNome.isNotBlank() && novoComentario.isNotBlank()) {
                                comentarios.add(Comentario(autorNome, novoComentario, 5))
                                autorNome = ""
                                novoComentario = ""
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE87735)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Enviar Avaliação")
                    }
                }
            }

            // Lista de Comentários
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(comentarios) { item ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(item.autor, fontWeight = FontWeight.Bold)
                                Row {
                                    repeat(item.nota) {
                                        Icon(
                                            Icons.Default.Star,
                                            contentDescription = null,
                                            tint = Color(0xFFFFB300),
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(item.texto, color = Color.DarkGray, fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComentariosReviewScreenPreview() {
    Viajai_MAFTheme {
        ComentariosReviewScreen(
            pousadaId = 1,
            onVoltar = {}
        )
    }
}