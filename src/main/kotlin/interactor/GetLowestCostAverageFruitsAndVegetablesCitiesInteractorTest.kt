package interactor

import fake_data.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GetLowestCostAverageFruitsAndVegetablesCitiesInteractorTest {
    private lateinit var interactor: GetLowestCostAverageFruitsAndVegetablesCitiesInteractor


    @Test
    fun `should return null when data is low quality `() {
        // low quality data
        interactor = GetLowestCostAverageFruitsAndVegetablesCitiesInteractor(LowQualityData())
        // When
        val actualCity = interactor.execute(10)
        // Then
        assertNotNull(actualCity)
    }

    @Test
    fun `should return null when Data is Invalid `() {
        //given invalid data
        interactor = GetLowestCostAverageFruitsAndVegetablesCitiesInteractor(NullData())
        // When
        val actualCity = interactor.execute(10)
        // Then
        assertNotNull(actualCity)
    }

    @Test
    fun `should return null when given empty list`() {
        // given empty list
        interactor = GetLowestCostAverageFruitsAndVegetablesCitiesInteractor(FakeEmptyList())

        // When
        val actualCity = interactor.execute(0)

        // Then
        assertNotNull(actualCity)
    }

    @Test
    fun `should return Cities With Lowest Avg Fru and Veg Cost `() {
        // given high quality data
        interactor = GetLowestCostAverageFruitsAndVegetablesCitiesInteractor(FakeData())
        val expectedCity = FakeData().getAllCitiesData().map { it.cityName }
        //when
        val actualCity = interactor.execute(8)
        //then
        assertEquals(expectedCity, actualCity)
    }




}

