package test.interactor

import fake_data.FakeBrandDataWithNull
import fake_data.FakeDataWithNull
import fake_data.FakeEmptyCity
import fake_data.FakeFullData
import interactor.GetTopCitiesForFashionableClothesInteractor
import model.CityEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class GetTopCitiesForFashionableClothesInteractorTest {
    private lateinit var fakeFullData : FakeFullData
    private lateinit var fakeDataWithNull: FakeDataWithNull
    private lateinit var fakeBrandDataWithNull: FakeBrandDataWithNull
    private lateinit var fakeEmptyCity: FakeEmptyCity


    @BeforeAll
    fun setup()
    {
        fakeFullData= FakeFullData()
        fakeDataWithNull= FakeDataWithNull()
        fakeBrandDataWithNull = FakeBrandDataWithNull()
        fakeEmptyCity = FakeEmptyCity()


    }
    @Test
    fun should_ReturnTop5CitiesName_When_EnterListOfCitiesWithFullData() {
        //given list of five city
        val listOfCities =GetTopCitiesForFashionableClothesInteractor(fakeFullData)
        //when find top 5 cities for suitable price
        val topFiveCities =listOfCities.execute(5)
        //then check the result
        assertEquals(listOf("aswan","Giza","tanta","alexandria","cairo"),topFiveCities)
    }
    @Test
    fun should_ReturnTop5CitiesName_When_EnterListOfCitiesWithOneOrMoreBrand() {
        //given list of more than five cities with at least one brand or more for a city
        val listOfCities = GetTopCitiesForFashionableClothesInteractor(fakeBrandDataWithNull)
        //when find top 5 cities for suitable price
        val topFiveCities = listOfCities.execute(5)
        //then check the result
        assertEquals(listOf("El-minya","aswan","Giza","tanta","alexandria"),topFiveCities)
    }
    @Test
    fun should_ExcludeCity_When_AllClothesPriceHaveNullValue()
    {
        //given list contain city with null value for all clothes price
        val listOfCities =GetTopCitiesForFashionableClothesInteractor(fakeDataWithNull)
        //when find top cities and exclude city have no brand for clothes
        val topCities = listOfCities.execute(5)
        //then check the result
        assertEquals(listOf("aswan","Giza","tanta","cairo"),topCities)
    }
    @Test
    fun should_ReturnEmptyList_When_LimitIsZero()
    {
        //given Empty list
        val limit=0
        val listOfCities =GetTopCitiesForFashionableClothesInteractor(fakeEmptyCity)
        //when find  top brand cities names
        val result = listOfCities.execute(limit)
        //then check the result
        assertEquals(emptyList<CityEntity>(),result)
    }
    @Test
    fun should_ReturnEmptyList_When_LimitIsNegative()
    {
        //given Empty list
        val limit = -1
        val listOfCities =GetTopCitiesForFashionableClothesInteractor(fakeFullData)
        //when find  top brand cities names
        val result = listOfCities.execute(limit)
        //then check the result
        assertEquals(emptyList<CityEntity>(),result)
    }


}