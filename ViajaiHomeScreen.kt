package com.example.viajai_maf

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ViajaiHomeScreen(
    onNavigateToChecklist: () -> Unit,
    onNavigateToDestinos: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val context = LocalContext.current

    // Cores fiéis ao layout
    val orangePrimary = Color(0xFFE47E45)
    val darkRedSecondary = Color(0xFF8B261D)
    val limeProgress = Color(0xFF76FF03)

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(orangePrimary, darkRedSecondary)
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = darkRedSecondary, modifier = Modifier.size(32.dp)) },
                    selected = true,
                    onClick = onNavigateToHome
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Listas", tint = darkRedSecondary, modifier = Modifier.size(32.dp)) },
                    selected = false,
                    onClick = onNavigateToDestinos
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = "Busca", tint = darkRedSecondary, modifier = Modifier.size(32.dp)) },
                    selected = false,
                    onClick = { Toast.makeText(context, "Ir para Busca", Toast.LENGTH_SHORT).show() }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Perfil", tint = darkRedSecondary, modifier = Modifier.size(32.dp)) },
                    selected = false,
                    onClick = { Toast.makeText(context, "Ir para Perfil", Toast.LENGTH_SHORT).show() }
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
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Cabecalho superior fixo com fundo claro/transparente
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Viajaí",
                        color = orangePrimary,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Normal
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Título de Seção 1
                    Text(
                        text = "Sua Próxima Viagem",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )

                    // Card de Próxima Viagem
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "São Paulo, SP",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )

                            // Caixinha Interna Laranja com Detalhes da Viagem
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, orangePrimary.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                                    .padding(12.dp)
                            ) {
                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Icon(
                                                Icons.Default.DateRange,
                                                contentDescription = null,
                                                tint = orangePrimary,
                                                modifier = Modifier.size(28.dp)
                                            )
                                            Text(
                                                text = "02/09/2026",
                                                color = orangePrimary,
                                                fontSize = 18.sp
                                            )
                                        }
                                        Text(
                                            text = "402 KM",
                                            color = orangePrimary,
                                            fontSize = 18.sp
                                        )
                                    }

                                    Row {
                                        Text(
                                            text = "Duração estimada: ",
                                            color = orangePrimary,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "5H 48Min",
                                            color = orangePrimary,
                                            fontSize = 18.sp
                                        )
                                    }
                                }
                            }

                            // Barra de Progresso
                            Text(
                                text = "Planejamento: 75%",
                                color = Color.Black,
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(top = 12.dp, bottom = 6.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(18.dp)
                                    .border(1.dp, Color.Gray, RoundedCornerShape(2.dp))
                                    .background(Color.White)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth(0.75f)
                                        .background(limeProgress)
                                )
                            }

                            // Botão Continuar Viagem
                            Button(
                                onClick = {
                                    Toast.makeText(context, "Continuando viagem...", Toast.LENGTH_SHORT).show()
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = orangePrimary),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .padding(top = 12.dp)
                            ) {
                                Text("Continuar Viagem", color = Color.White, fontSize = 13.sp)
                            }
                        }
                    }

                    // Título de Seção 2
                    Text(
                        text = "Planejamento",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(top = 20.dp, bottom = 12.dp)
                    )

                    // Grid 2x2 com os Cards do Planejamento
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Linha 1: Check List e Bagagem
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            PlannerGridItem(
                                title = "Check List",
                                value = "8/12 Itens",
                                icon = {
                                    Icon(
                                        Icons.Default.Check,
                                        contentDescription = null,
                                        tint = orangePrimary,
                                        modifier = Modifier.size(36.dp)
                                    )
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                onNavigateToChecklist()
                            }

                            PlannerGridItem(
                                title = "Bagagem",
                                value = "5/8",
                                icon = { Text("🧳", fontSize = 32.sp) },
                                modifier = Modifier.weight(1f)
                            ) {
                                Toast.makeText(context, "Abrir Bagagem", Toast.LENGTH_SHORT).show()
                            }
                        }

                        // Linha 2: Total Gasto e Lugares Salvos
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            PlannerGridItem(
                                title = "Total gasto",
                                value = "R$ 1.650",
                                icon = { Text("📑", fontSize = 32.sp) },
                                modifier = Modifier.weight(1f)
                            ) {
                                Toast.makeText(context, "Abrir Gastos", Toast.LENGTH_SHORT).show()
                            }

                            PlannerGridItem(
                                title = "Lugares Salvos",
                                value = "6 Salvos",
                                icon = {
                                    Icon(
                                        Icons.Default.LocationOn,
                                        contentDescription = null,
                                        tint = orangePrimary,
                                        modifier = Modifier.size(36.dp)
                                    )
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Toast.makeText(context, "Abrir Lugares Salvos", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
fun PlannerGridItem(
    title: String,
    value: String,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(130.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            icon()
            Column {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = value,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViajaiHomeScreenPreview() {
    MaterialTheme {
        ViajaiHomeScreen(
            onNavigateToChecklist = {},
            onNavigateToDestinos = {},
            onNavigateToHome = {}
        )
    }
}
