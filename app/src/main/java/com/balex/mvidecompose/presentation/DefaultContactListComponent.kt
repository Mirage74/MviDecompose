package com.balex.mvidecompose.presentation

import com.arkivanov.decompose.ComponentContext
import com.balex.mvidecompose.core.componentScope
import com.balex.mvidecompose.data.RepositoryImpl
import com.balex.mvidecompose.domain.Contact
import com.balex.mvidecompose.domain.GetContactsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DefaultContactListComponent(
    componentContext: ComponentContext,
    val onEditingContactRequested: (Contact) -> Unit,
    val onAddContactRequested: () -> Unit
) : ContactListComponent, ComponentContext by componentContext {


    private val repository = RepositoryImpl
    private val getContactsUseCase = GetContactsUseCase(repository)
    private val coroutineScope = componentScope()


    override val model: StateFlow<ContactListComponent.Model> = getContactsUseCase()
        .map {
            ContactListComponent.Model(it)
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Lazily,
            initialValue = ContactListComponent.Model(listOf())
        )

    override fun onContactClicked(contact: Contact) {
        onEditingContactRequested(contact)
    }

    override fun onAddContactClicked() {
        onAddContactRequested()
    }
}

