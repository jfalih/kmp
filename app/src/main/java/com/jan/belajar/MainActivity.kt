package com.jan.belajar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jan.belajar.presentation.components.atoms.BelajarTheme
import com.jan.belajar.presentation.features.home.HomePage
import com.jan.belajar.presentation.features.onboarding.OnboardingPage
import com.jan.belajar.presentation.features.songdetail.SongDetailPage
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinAndroidContext {
                BelajarTheme {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "onboarding") {
                        composable("onboarding") { OnboardingPage(navController = navController) }
                        composable("home") { HomePage(navController = navController) }
                        composable("songDetail") { SongDetailPage() }
                    }
                }
            }
        }
    }
}
