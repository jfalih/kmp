package com.jan.belajar.presentation.features.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jan.belajar.presentation.components.atoms.Typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPage(navController: NavController) {
    val onboardingViewModel: OnboardingViewModel = viewModel()
    val pages = onboardingViewModel.pages
    val isLast = onboardingViewModel.last

    val pagerState = rememberPagerState(pageCount = {
        pages.size
    })

    val coroutineScope = rememberCoroutineScope()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState, modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                PageContent(
                    url = pages[it].url,
                    title = pages[it].title,
                    description = pages[it].description,
                    highlight = pages[it].highlight
                )
            }
            PageIndicator(size = isLast, active = pagerState.currentPage)
            Column(
                modifier = Modifier
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth().height(54.dp),
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        coroutineScope.launch {
                            if (pagerState.currentPage !== isLast) {
                                val nextPage = pagerState.currentPage + 1;
                                pagerState.animateScrollToPage(nextPage);
                                return@launch;
                            }
                            navController.navigate("home")
                        }
                    }) {
                    Text(
                        text = "Continue",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun PageIndicator(size: Int, active: Int) {
    Row() {
        (0..size).forEach {
            Box(
                modifier = Modifier
                    .size(width = if (active == it) 40.dp else 8.dp, height = 8.dp)
                    .background(
                        color = if (active == it) MaterialTheme.colorScheme.primary else Color.White,
                        shape = MaterialTheme.shapes.small
                    )
            )
            if (it < size) Spacer(modifier = Modifier.width(8.dp));
        }
    }
}


@Composable
fun LoadingScreen() {
    // Display a loading indicator
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.Black)
    }
}

@Composable
fun PageContent(url: String, title: String, description: String, highlight: String) {
    val text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.White,
            )
        ) {
            append(title)
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
        ) {
            append(highlight)
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(24.dp),
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = Color.White,
            style = Typography.titleLarge
        )
        Text(
            text = description,
            textAlign = TextAlign.Center,
            color = Color.White,
            style = Typography.bodyLarge
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(20.dp)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current).data(url).crossfade(true)
                    .build(),
                contentScale = ContentScale.Fit,
                contentDescription = null,
            )
        }
    }
}