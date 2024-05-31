package com.balex.mvidecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.balex.mvidecompose.domain.Contact
import com.balex.mvidecompose.ui.content.AddContact
import com.balex.mvidecompose.ui.content.Contacts
import com.balex.mvidecompose.ui.content.EditContact
import com.balex.mvidecompose.ui.theme.MviDecomposeTestTheme

class MainActy : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var screen by remember {
                mutableStateOf<Screen>(Screen.ContactList)
            }
            MviDecomposeTestTheme {
                when (val currentScreen = screen) {
                    Screen.AddContact -> {
                        AddContact(
                            onContactSaved = {
                                screen = Screen.ContactList
                            }
                        )
                    }

                    Screen.ContactList -> {
                        Contacts(
                            onAddContactClick = {
                                screen = Screen.AddContact
                            },
                            onContactClick = {
                                screen = Screen.EditContact(it)
                            }
                        )
                    }

                    is Screen.EditContact -> {
                        EditContact(
                            contact = currentScreen.contact,
                            onContactChanged = {
                                screen = Screen.ContactList
                            }
                        )
                    }
                }
            }
        }
    }
}

sealed class Screen {
    object ContactList : Screen()
    object AddContact : Screen()

    data class EditContact(val contact: Contact) : Screen()
}