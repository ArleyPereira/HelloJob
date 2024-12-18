package br.com.hellodev.hellojob.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.hellojob.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    containerColor = HelloTheme.colorScheme.screen.background
                ) { paddingValues ->
                    AppNavHost(
                        navHostController = rememberNavController(),
//                        modifier = Modifier
//                            .padding(paddingValues)
                    )
                }
            }
        }
    }
}