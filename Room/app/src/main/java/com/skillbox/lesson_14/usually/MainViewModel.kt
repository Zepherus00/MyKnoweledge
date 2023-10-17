/*
package com.skillbox.lesson_14.usually

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val userDao: UserDao) : ViewModel() {

    val allUsers = this.userDao.getAll("name 0")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun onAddBtn() {
        val size = allUsers.value.size
        viewModelScope.launch {
            userDao.insert(
                NewUser(
                    firstName = "Name $size",
                    lastName = "LastName $size",
                    age = size
                )
            )
        }
    }

    fun onUpdateBtn() {
        viewModelScope.launch {
            allUsers.value.lastOrNull()?.let {
                val user = it.copy(
                    lastName = "Petrov"
                )
                userDao.update(*/
/*user*//*
"user 10")
            }
        }
    }

    fun onDeleteBtn() {
        viewModelScope.launch {
            allUsers.value.lastOrNull()?.let { userDao.delete(it) }
        }
    }
}*/
