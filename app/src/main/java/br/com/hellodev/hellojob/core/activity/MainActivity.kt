package br.com.hellodev.hellojob.core.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import br.com.hellodev.hellojob.presenter.features.onboarding.screen.WelcomeScreen
import br.com.hellodev.hellojob.presenter.ui.theme.HelloTheme

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
                ) { innerPadding ->
                    WelcomeScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}