package interactor

import fakedata.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GetCountriesNamesWithHighestCokePriceTest {
    private lateinit var getCountriesNamesWithHighestCokePrice: GetCountriesNamesWithHighestCokePrice
    private lateinit var fakeCarbonatedTaxesOnCokeCountriesData: FakeCarbonatedTaxesOnCokeCountriesData
    private lateinit var fakeDuplicatedList: FakeDuplicatedList
    private lateinit var fakeShortCarbonatedList: FakeShortCarbonatedList

    @BeforeAll
    fun setup() {
        fakeCarbonatedTaxesOnCokeCountriesData = FakeCarbonatedTaxesOnCokeCountriesData()
        fakeDuplicatedList = FakeDuplicatedList()
        fakeShortCarbonatedList = FakeShortCarbonatedList()
        getCountriesNamesWithHighestCokePrice = GetCountriesNamesWithHighestCokePrice(fakeCarbonatedTaxesOnCokeCountriesData)
    }

    @Test
    fun `should return False When The List of Pairs Size Is more than 10`() {
        // given Country limit and Data
        val countriesLimit = 10
        // when pairOfCountryAndPrices is less than 10 countries
        val pairOfCountryAndPrices = getCountriesNamesWithHighestCokePrice.execute(limitOfCountries = countriesLimit)
        // then
        assertTrue(pairOfCountryAndPrices.size <= 10)
    }

    @Test
    fun `should Return the top ten Countries with given data`() {
        // given country limit and Data
        val countriesLimit = 10
        val expected: String =
            listOf(
                Pair("france", 12.7123),
                Pair("japan", 10.356),
                Pair("korea", 9.3),
                Pair("turkey", 9.3),
                Pair("usa", 6.0),
                Pair("egypt", 5.0),
                Pair("germany", 3.5),
                Pair("pakistan", 1.5),
                Pair("algeria", 1.0),
                Pair("china", 0.5)
            ).toString()
        // when pairOfCountryAndPrices is not Equal to top 10 countries data
        val pairOfCountryAndPrices = getCountriesNamesWithHighestCokePrice.execute(limitOfCountries = countriesLimit)
        val actual: String = pairOfCountryAndPrices.toString()
        // then
        assertEquals(expected, actual)
    }

    @Test
    fun `should return List of Ten Countries when Two countries Does not Have The Same Name`() {
        // given country limit and Data
        // when pairOfCountryAndPrices have duplicates
        val pairOfCountryAndPrices = getCountriesNamesWithHighestCokePrice.execute(10)
        // then
        assertEquals(pairOfCountryAndPrices.distinctBy { it.first }, pairOfCountryAndPrices)
    }

    @Test
    fun `should return list with no negative in the Prices`() {
        // given country limit and Data
        val limit = 10
        // when pairOfCountryAndPrices null prices
        val pairOfCountryAndPrices = getCountriesNamesWithHighestCokePrice.execute(limitOfCountries = limit)
        // then
        assertEquals(pairOfCountryAndPrices, pairOfCountryAndPrices.filter { it.second >= 0 })
    }
}
