package test.interactor

import fake_data.FakeData
import fake_data.FakeEmptyList
import fake_data.LowQualityData
import fake_data.NullData
import interactor.GetHighestSalaryAverageCitiesNamesInteractor
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GetHighestSalaryAverageCitiesNamesInteractorTest {
    private lateinit var interactor: GetHighestSalaryAverageCitiesNamesInteractor


    @Test
    fun `should return null when data is low quality `() {
        // low quality data
        interactor = GetHighestSalaryAverageCitiesNamesInteractor(LowQualityData())
        // When
        val actualCity = interactor.execute(10)
        // Then
        assertNotNull(actualCity)
    }

    @Test
    fun `should return null when Data is Invalid `() {
        //given invalid data
        interactor = GetHighestSalaryAverageCitiesNamesInteractor(NullData())
        // When
        val actualCity = interactor.execute(10)
        // Then
        assertNotNull(actualCity)
    }

    @Test
    fun `should return null when given empty list`() {
        // given empty list
        interactor = GetHighestSalaryAverageCitiesNamesInteractor(FakeEmptyList())

        // When
        val actualCity = interactor.execute(0)

        // Then
        assertNotNull(actualCity)
    }

    @Test
    fun `should return Cities With Highest Avg Salary `() {
        // given high quality data
        interactor = GetHighestSalaryAverageCitiesNamesInteractor(FakeData())
        val expectedCity = FakeData().getAllCitiesData().map { it.cityName }
        //when
        val actualCity = interactor.execute(3)
        //then
        assertEquals(expectedCity, actualCity)
    }



}