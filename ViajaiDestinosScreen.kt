package com.example.viajai_maf

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Pousada(
    val id: Int,
    val nome: String,
    val tipo: String,
    val nota: String,
    val avaliacoes: String,
    val precoTotal: String,
    val imagemResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViajaiDestinosScreen(
    onNavigateToChecklist: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToDestinos: () -> Unit,
    onNavigateToEditar: () -> Unit,
    onNavigateToComentarios: (Int) -> Unit
) {
    val context = LocalContext.current

    var exibirMenuViagem by remember { mutableStateOf(false) }
    var textoBuscaPousada by remember { mutableStateOf("") }

    val listaPousadasOriginal = remember {
        listOf(
            Pousada(1, "Pousada Maraka", "Imóvel por temporada", "4.73", "818", "R$ 1.501", android.R.drawable.ic_menu_gallery),
            Pousada(2, "Pousada Zamberlan", "Pousada tradicional", "4.86", "144", "R$ 2.795", android.R.drawable.ic_menu_gallery),
            Pousada(3, "Casa São Paulo", "Hospedagem inteira", "4.91", "56", "R$ 1.505", android.R.drawable.ic_menu_gallery)
        )
    }

    val pousadasFiltradas = listaPousadasOriginal.filter {
        it.nome.contains(textoBuscaPousada, ignoreCase = true)
    }

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFFE47E45), Color(0xFF8B261D))
    )

    Scaffold(
        floatingActionButton = {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (exibirMenuViagem) {
                    Button(
                        onClick = { Toast.makeText(context, "Navegando para Planejar...", Toast.LENGTH_SHORT).show() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Planejar Viagem", color = Color(0xFF8B261D), fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = onNavigateToEditar,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Editar Viagem", color = Color(0xFF8B261D), fontWeight = FontWeight.Bold)
                    }
                }

                FloatingActionButton(
                    onClick = { exibirMenuViagem = !exibirMenuViagem },
                    containerColor = Color.White,
                    contentColor = Color(0xFF8B261D),
                    shape = RoundedCornerShape(28.dp),
                    modifier = Modifier.size(56.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Menu Viagem")
                }
            }
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                val darkRed = Color(0xFF8B261D)
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = darkRed, modifier = Modifier.size(32.dp)) },
                    label = { Text("Home") },
                    selected = false,
                    onClick = onNavigateToHome
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Listas", tint = darkRed, modifier = Modifier.size(32.dp)) },
                    label = { Text("Listas") },
                    selected = true,
                    onClick = onNavigateToDestinos
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = "Busca", tint = darkRed, modifier = Modifier.size(32.dp)) },
                    label = { Text("Busca") },
                    selected = false,
                    onClick = {}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Perfil", tint = darkRed, modifier = Modifier.size(32.dp)) },
                    label = { Text("Perfil") },
                    selected = false,
                    onClick = { Toast.makeText(context, "Perfil clicado", Toast.LENGTH_SHORT).show() }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(gradientBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    IconButton(onClick = onNavigateToHome) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                }

                Text(
                    text = "Destinos",
                    color = Color.White,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Icon(
                                painter = painterResource(id = android.R.drawable.ic_menu_edit),
                                contentDescription = "Editar Destino",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        onNavigateToEditar()
                                    }
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "De: CWB, PR",
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black
                                )
                                Text(
                                    text = "Para: SP, SP",
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black
                                )
                            }

                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFE47E45)),
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .width(105.dp)
                                    .height(70.dp)
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Data:",
                                        color = Color.White,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                    Text(
                                        text = "02/09/26",
                                        color = Color.White,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "Distância",
                            color = Color.White,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            shape = RoundedCornerShape(24.dp),
                            modifier = Modifier.width(130.dp).height(45.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "402 km",
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "Duração",
                            color = Color.White,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            shape = RoundedCornerShape(24.dp),
                            modifier = Modifier.width(130.dp).height(45.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "5h 48m",
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("ℹ️", fontSize = 16.sp)
                    Text(
                        text = "O tempo e a distância da viagem podem variar conforme o clima, as condições da via, acidentes ou obras no trajeto.",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 11.sp,
                        lineHeight = 14.sp
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Gastos Estimados",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text("📑", fontSize = 32.sp)
                                Text(
                                    text = "Total: R$ 1.650",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                            Button(
                                onClick = {
                                    Toast.makeText(context, "Exibindo detalhamento dos gastos...", Toast.LENGTH_SHORT).show()
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE47E45)),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Ver Gastos", color = Color.White, fontSize = 12.sp)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = textoBuscaPousada,
                    onValueChange = { novoTexto -> textoBuscaPousada = novoTexto },
                    label = { Text("Buscar pousada por nome...", color = Color.White) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.White) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White.copy(alpha = 0.7f),
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Pousadas Próximas  >",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${pousadasFiltradas.size} encontrada(s)",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 12.sp
                        )
                    }

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(pousadasFiltradas) { pousada ->
                            Card(
                                modifier = Modifier
                                    .width(160.dp)
                                    .clickable {
                                        onNavigateToComentarios(pousada.id)
                                    },
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                shape = RoundedCornerShape(12.dp),
                                elevation = CardDefaults.cardElevation(2.dp)
                            ) {
                                Column {
                                    Image(
                                        painter = painterResource(id = pousada.imagemResId),
                                        contentDescription = pousada.nome,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(90.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                    Column(modifier = Modifier.padding(8.dp)) {
                                        Text(
                                            text = pousada.nome,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp,
                                            color = Color.Black
                                        )
                                        Text(text = pousada.tipo, fontSize = 10.sp, color = Color.Gray)
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(vertical = 2.dp)
                                        ) {
                                            Text("⭐", fontSize = 10.sp)
                                            Text(
                                                " ${pousada.nota} (${pousada.avaliacoes})",
                                                fontSize = 10.sp,
                                                color = Color.Black
                                            )
                                        }
                                        Text(
                                            text = "Total: ${pousada.precoTotal}",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 11.sp,
                                            color = Color(0xFF8B261D)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViajaiDestinosScreenPreview() {
    MaterialTheme {
        ViajaiDestinosScreen(
            onNavigateToChecklist = {},
            onNavigateToHome = {},
            onNavigateToDestinos = {},
            onNavigateToEditar = {},
            onNavigateToComentarios = {}
        )
    }
}
