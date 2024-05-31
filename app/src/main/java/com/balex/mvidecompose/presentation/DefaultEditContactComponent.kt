package com.balex.mvidecompose.presentation

import com.balex.mvidecompose.data.RepositoryImpl
import com.balex.mvidecompose.domain.Contact
import com.balex.mvidecompose.domain.EditContactUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultEditContactComponent(
    private val contact: Contact
) : EditContactComponent {
    private val repository = RepositoryImpl
    private val editContactUseCase = EditContactUseCase(repository)

    private val _model = MutableStateFlow(
        EditContactComponent.Model(username = contact.username, phone = contact.phone)
    )
    override val model: StateFlow<EditContactComponent.Model>
        get() = _model.asStateFlow()

    override fun onUsernameChanged(username: String) {
        _model.value = model.value.copy(username = username)
    }

    override fun onPhoneChanged(phone: String) {
        _model.value = model.value.copy(phone = phone)
    }

    override fun onSaveContactClicked() {
        val (username, phone) = model.value
        editContactUseCase(
            contact.copy(
                username = username,
                phone = phone
            )
        )
    }
}