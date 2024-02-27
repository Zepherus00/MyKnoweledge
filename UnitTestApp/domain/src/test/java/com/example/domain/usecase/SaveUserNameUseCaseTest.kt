package com.example.domain.usecase

import com.example.domain.models.SaveUserNameParam
import com.example.domain.models.Username
import com.example.domain.repository.UserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

class SaveUserNameUseCaseTest {

    val userRepository = mock<UserRepository>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(userRepository)
    }

    @Test
    fun shouldNotSaveDataIfNameWasAlreadySave() {
        val testData = Username("TestName", "test last name")
        Mockito.`when`(userRepository.getName()).thenReturn(testData)

        val useCase = SaveUserNameUseCase(userRepository = userRepository)
        val testParams = SaveUserNameParam(name = "TestName")
        val actual = useCase.execute(testParams)
        val expected = true

        Assertions.assertEquals(expected, actual)

        Mockito.verify(userRepository, Mockito.never()).saveName(saveParam = any())
    }

    @Test
    fun shouldReturnTrueIfSaveWasSuccessful() {
        val testData = Username("TestName", "test last name")
        Mockito.`when`(userRepository.getName()).thenReturn(testData)

        val expected = true
        val testParams = SaveUserNameParam(name = "new TestName")
        Mockito.`when`(userRepository.saveName(saveParam = testParams)).thenReturn(expected)

        val useCase = SaveUserNameUseCase(userRepository = userRepository)
        val actual = useCase.execute(testParams)

        Assertions.assertEquals(expected, actual)

        Mockito.verify(userRepository, Mockito.times(1)).saveName(saveParam = testParams)
    }

    @Test
    fun shouldReturnFalseIfSaveWasSuccessful() {
        val testData = Username("TestName", "test last name")
        Mockito.`when`(userRepository.getName()).thenReturn(testData)

        val expected = false
        val testParams = SaveUserNameParam(name = "new TestName")
        Mockito.`when`(userRepository.saveName(saveParam = testParams)).thenReturn(expected)

        val useCase = SaveUserNameUseCase(userRepository = userRepository)
        val actual = useCase.execute(testParams)

        Assertions.assertEquals(expected, actual)

        Mockito.verify(userRepository, Mockito.times(1)).saveName(saveParam = testParams)
    }
}