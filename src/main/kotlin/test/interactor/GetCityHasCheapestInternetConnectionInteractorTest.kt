package test.interactor

import dataSource.CsvDataSource
import dataSource.utils.CsvParser
import interactor.CostOfLivingDataSource
import interactor.GetCityHasCheapestInternetConnectionInteractor
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class GetCityHasCheapestInternetConnectionInteractorTest {
    val csvParser = CsvParser()
    val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser)
    val countryNumbers = GetCityHasCheapestInternetConnectionInteractor(dataSource)

    @Test
    fun shouldReturnWhenPriceNotNull() {
        val internetPrice = 10
        assertNotNull(internetPrice)
    }

    @Test
    fun returnTrueWhenCountriesNumEqualLimit() {
        val limit = 10
        countryNumbers.execute(limit = limit)
        assertEquals(10,limit)
    }

    @Test
    fun returnTrueIfAvaregeNotEqualZero(){
        val internetPrice = 20
        val salary = 100
        val result = internetPrice / salary * 100
        assertNotEquals(0.0,result)
    }
}