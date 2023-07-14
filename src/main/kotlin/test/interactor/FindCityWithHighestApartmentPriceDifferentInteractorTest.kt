package test.interactor

import fake_data.*
import interactor.FindCityWithHighestApartmentPriceDifferentInteractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FindTheHighestDifferenceInRentalPricesInteractorTest {
    private lateinit var interactor: FindCityWithHighestApartmentPriceDifferentInteractor

    @Test
    fun `should return city with highest difference price when given high quality data`() {
        // given high quality data
        interactor = FindCityWithHighestApartmentPriceDifferentInteractor(FakeData())
        val expectedCity = FakeData().getAllCitiesData()[1]
        //when
        val actualCity = interactor.execute()
        //then
        assertEquals(expectedCity, actualCity)
    }

    @Test
    fun `should return city with highest difference price when given mixed data`() {
        //given mixed data between high quality ,low quality and invalid data
        interactor = FindCityWithHighestApartmentPriceDifferentInteractor(MixedFakeData())
        val expectedCity = MixedFakeData().getAllCitiesData()[0]

        // When
        val actualCity = interactor.execute()

        // Then
        assertEquals(expectedCity, actualCity)
    }

    @Test
    fun `should return null when given empty list`() {
        // given empty list
        interactor = FindCityWithHighestApartmentPriceDifferentInteractor(FakeEmptyList())

        // When
        val actualCity = interactor.execute()

        // Then
        assertNull(actualCity)
    }

    @Test
    fun `should return null when given invalid null data`() {
        //given invalid data
        interactor = FindCityWithHighestApartmentPriceDifferentInteractor(NullData())
        // When
        val actualCity = interactor.execute()
        // Then
        assertNull(actualCity)
    }

    @Test
    fun `should return null when given low quality data`() {
        //given low quality data
        interactor = FindCityWithHighestApartmentPriceDifferentInteractor(LowQualityData())
        // When
        val actualCity = interactor.execute()
        // Then
        assertNull(actualCity)
    }
}