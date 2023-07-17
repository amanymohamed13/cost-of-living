package test.interactor

import GetAvarageSalaryForEachCityInCountry
import dataSource.CsvDataSource
import dataSource.utils.CsvParser
import fake_data.FakeData
import fake_data.LowQualityData
import fake_data.NullData
import interactor.CostOfLivingDataSource
import interactor.GetHighestSalaryAverageCitiesNamesInteractor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class GetAvarageSalaryForEachCityInCountryTest {
    val csvParser = CsvParser()
    val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser)
    private var interactorTest: GetAvarageSalaryForEachCityInCountry=GetAvarageSalaryForEachCityInCountry(dataSource)

    @Test
    fun returnIfCountryNameIsEmpty() {
        val country:String = ""
        assertEquals("",country)
    }
    @Test
    fun returnIfCountryNameIsNotNull() {
        val country:String? = "egypt"
        assertNotEquals(null,country)
    }

    @Test
    fun returnIfCountryIsCapitalized() {
        val country = "EGYPT"
        val test = interactorTest.correctTheCountryName(country)
        assertNotEquals("Egypt",test)
    }


    @Test
    fun shouldReturnNullWhenDataIsLowQuality() {

        interactorTest = GetAvarageSalaryForEachCityInCountry(LowQualityData())

        val actualCountry = interactorTest.execute("Egypt")

        assertNotNull(actualCountry)
    }
    @Test
    fun shouldReturnNullWhenDataIsInvalid() {

        interactorTest = GetAvarageSalaryForEachCityInCountry(NullData())

        val actualCountry = interactorTest.execute("Egypt")

        assertNotNull(actualCountry)
    }

    @Test
    fun shouldReturnCitiesAndThereAvarageSalary() {

        interactorTest = GetAvarageSalaryForEachCityInCountry(FakeData())

        val excepctedCountry = FakeData().getAllCitiesData().map { it.cityName to it.averageMonthlyNetSalaryAfterTax }
        val acctualCountry = interactorTest.execute("country")
        assertEquals(excepctedCountry,acctualCountry)
    }

}