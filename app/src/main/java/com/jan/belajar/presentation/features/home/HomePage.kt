package com.jan.belajar.presentation.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jan.belajar.presentation.components.molecules.Category

import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage(navController: NavController, homeViewModel: HomeViewModel = koinViewModel()) {
    val state = homeViewModel.state.value
    val categories = state.categories

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        LazyColumn(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Hello, ", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "Jan Falih",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(5) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color(0xFF1F2329),
                                    shape = CircleShape
                                )
                                .padding(horizontal = 18.dp, vertical = 6.dp)
                        ) {
                            Text("Healing Music")
                        }
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Featured",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "See All",
                        modifier = Modifier.clickable {
                            navController.navigate("onboarding")
                        },
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
            item {
                if (state.loading) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp),
                            color = Color.White,
                        )
                    }
                } else {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(categories.size) {
                            Category(
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate("songDetail")
                                    }
                                    .background(
                                        color = if (it % 2 == 0) {
                                            Color(0xFFA28AEF)
                                        } else {
                                            Color(0xFFD3E560)
                                        },
                                        shape = RoundedCornerShape(36.dp)
                                    ),
                                title = categories[it].title,
                                type = categories[it].type,
                                min = categories[it].min.toString()
                            )
                        }
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Most Playing",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "See All",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
            items(5) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF1F2329),
                            shape = RoundedCornerShape(36.dp)
                        )
                        .padding(24.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = if (it % 2 == 0) {
                                        Color(0xFFD3E560)
                                    } else {
                                        Color(0xFFA28AEF)
                                    }, shape = RoundedCornerShape(14.dp)
                                )
                                .padding(10.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Filled.Home,
                                tint = Color.Black,
                                contentDescription = "Home"
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                        ) {
                            Text(
                                "Deep Sleep",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text("Music", style = MaterialTheme.typography.bodySmall)
                        }
                        Text("25 Min", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

