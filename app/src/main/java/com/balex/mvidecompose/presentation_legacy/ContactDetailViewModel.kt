package com.balex.mvidecompose.presentation_legacy

import androidx.lifecycle.ViewModel
import com.balex.mvidecompose.data.RepositoryImpl
import com.balex.mvidecompose.domain.AddContactUseCase
import com.balex.mvidecompose.domain.Contact
import com.balex.mvidecompose.domain.EditContactUseCase

class ContactDetailViewModel : ViewModel() {

    private val repository = RepositoryImpl

    private val addContactUseCase = AddContactUseCase(repository)
    private val editContactUseCase = EditContactUseCase(repository)

    fun addContact(username: String, phone: String) {
        addContactUseCase(username, phone)
    }

    fun editContact(contact: Contact) {
        editContactUseCase(contact)
    }
}