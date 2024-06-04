package com.balex.mvidecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.balex.mvidecompose.presentation.DefaultRootComponent
import com.balex.mvidecompose.ui.content.RootContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = DefaultRootComponent(defaultComponentContext())
        setContent {
            RootContent(component = component)
        }
    }
}