package com.katarina.androidchalenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.katarina.androidchalenge.di.projectKoinModules
import com.katarina.design.theme.AndroidChalengeTheme
import com.katarina.main.ui.ui.main.MainAppNav
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setupKoin()
        setContent {
            AndroidChalengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainAppNav(Modifier.padding(innerPadding))
                }
            }
        }
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(projectKoinModules)
        }
    }

}
