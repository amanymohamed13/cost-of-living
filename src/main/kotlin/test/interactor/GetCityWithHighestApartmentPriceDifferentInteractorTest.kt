package test.interactor

import fake_data.*
import interactor.GetCityWithHighestApartmentPriceDifferentInteractor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FindTheHighestDifferenceInRentalPricesInteractorTest {
    private lateinit var interactor: GetCityWithHighestApartmentPriceDifferentInteractor

    @Test
    fun `should return city with highest difference price when given high quality data`() {
        // given high quality data
        interactor = GetCityWithHighestApartmentPriceDifferentInteractor(FakeData())
        val expectedCity = FakeData().getAllCitiesData()[1]
        //when
        val actualCity = interactor.execute()
        //then
        assertEquals(expectedCity, actualCity)
    }

    @Test
    fun `should return city with highest difference price when given mixed data`() {
        //given mixed data between high quality ,low quality and invalid data
        interactor = GetCityWithHighestApartmentPriceDifferentInteractor(MixedFakeData())
        val expectedCity = MixedFakeData().getAllCitiesData()[0]

        // When
        val actualCity = interactor.execute()

        // Then
        assertEquals(expectedCity, actualCity)
    }

    @Test
    fun `should return null when given empty list`() {
        // given empty list
        interactor = GetCityWithHighestApartmentPriceDifferentInteractor(FakeEmptyList())

        // When
        val actualCity = interactor.execute()

        // Then
        assertNull(actualCity)
    }

    @Test
    fun `should return null when given invalid null data`() {
        //given invalid data
        interactor = GetCityWithHighestApartmentPriceDifferentInteractor(NullData())
        // When
        val actualCity = interactor.execute()
        // Then
        assertNull(actualCity)
    }

    @Test
    fun `should return null when given low quality data`() {
        //given low quality data
        interactor = GetCityWithHighestApartmentPriceDifferentInteractor(LowQualityData())
        // When
        val actualCity = interactor.execute()
        // Then
        assertEquals(null,actualCity)
    }
}