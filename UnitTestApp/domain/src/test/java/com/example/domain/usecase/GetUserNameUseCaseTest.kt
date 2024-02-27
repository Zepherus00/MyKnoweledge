package com.example.domain.usecase

import com.example.domain.models.Username
import com.example.domain.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

/*class TestRepository : UserRepository {
    override fun saveName(saveParam: SaveUserNameParam): Boolean {
        TODO("Not yet implemented")
    }

    override fun getName(): Username {
        return Username("text first name", "test last name")
    }
}*/

class GetUserNameUseCaseTest {

    val userRepository = mock<UserRepository>()

    @Test
    fun shouldReturnCorrectData() {

        val testData = Username("text first name", "test last name")
        Mockito.`when`(userRepository.getName()).thenReturn(testData)
        
        val useCase = GetUserNameUseCase(userRepository = userRepository)
        val actual = useCase.execute()
        val expected = Username("text first name", "test last name")
        Assertions.assertEquals(expected, actual)
    }
}