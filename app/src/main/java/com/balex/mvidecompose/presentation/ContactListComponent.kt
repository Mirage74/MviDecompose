package com.balex.mvidecompose.presentation

import com.balex.mvidecompose.domain.Contact
import kotlinx.coroutines.flow.StateFlow

interface ContactListComponent {

    val model: StateFlow<Model>

    fun onContactClicked(contact: Contact)

    fun onAddContactClicked()

    data class Model(
        val contactList: List<Contact>
    )
}