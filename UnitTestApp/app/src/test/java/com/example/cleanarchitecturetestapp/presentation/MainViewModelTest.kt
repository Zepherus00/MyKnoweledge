package com.example.cleanarchitecturetestapp.presentation

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.example.domain.models.SaveUserNameParam
import com.example.domain.models.Username
import com.example.domain.usecase.GetUserNameUseCase
import com.example.domain.usecase.SaveUserNameUseCase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class MainViewModelTest {

    val getUserNameUseCase = mock<GetUserNameUseCase>()
    val saveUserNameUseCase = mock<SaveUserNameUseCase>()
    lateinit var viewModel: MainViewModel

    @AfterEach
    fun afterEach() {
        Mockito.reset(getUserNameUseCase)
        Mockito.reset(saveUserNameUseCase)
        ArchTaskExecutor.getInstance().setDelegate(null)
    }

    @BeforeEach
    fun beforeEach() {
        viewModel = MainViewModel(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        )

        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }
        })
    }

    @Test
    fun shouldSaveUsernameAndReturnTrue() {
        val saveResult = true
        val testParams = SaveUserNameParam(name = "test text")
        Mockito.`when`(saveUserNameUseCase.execute(param = testParams)).thenReturn(saveResult)

        val testSaveText = "test text"
        viewModel.save(testSaveText)

        val expected = "Save result = true"
        val actual = viewModel.resultLive.value
        Assertions.assertEquals(expected, actual)

        Mockito
            .verify(saveUserNameUseCase, Mockito.times(1))
            .execute(param = testParams)
    }

    @Test
    fun shouldSaveUsernameAndReturnFalse() {
        val saveResult = false
        val testParams = SaveUserNameParam(name = "test text")
        Mockito.`when`(saveUserNameUseCase.execute(param = testParams)).thenReturn(saveResult)

        val testSaveText = "test text"
        viewModel.save(testSaveText)

        val expected = "Save result = false"
        val actual = viewModel.resultLive.value
        Assertions.assertEquals(expected, actual)

        Mockito
            .verify(saveUserNameUseCase, Mockito.times(1))
            .execute(param = testParams)
    }

    @Test
    fun shouldLoadUsername() {
        val testUserName = Username(
            firstName = "Test first name",
            lastName = "Test last name"
        )
        Mockito.`when`(getUserNameUseCase.execute()).thenReturn(testUserName)

        viewModel.load()
        val expected = "${testUserName.firstName} ${testUserName.lastName}"
        val actual = viewModel.resultLive.value

        Assertions.assertEquals(expected, actual)

        Mockito.verify(getUserNameUseCase, Mockito.times(1)).execute()
    }
}