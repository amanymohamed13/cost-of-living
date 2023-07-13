package interactor

import model.CityEntity
import model.MealsPrices
import kotlin.math.abs

//task 8
class GetMostSuitableCityForCompanyDinner(private val dataSource: CostOfLivingDataSource) {
    fun execute(
        boundary: Float = Float.MAX_VALUE,
        countries: List<String> = listOf("usa", "canada", "mexico")
    ): CityEntity? {
        val filteredCities = dataSource.getAllCitiesData().filteredByCountries(countries)
        val listOfAllPricesList = filteredCities.map { it.mealsPrices.getNotNullPrices() }
        val allPricesList = listOfAllPricesList.flatten()
        val exactPrice = allPricesList.getExactPrice() ?: return null
        val closestPrice = allPricesList.getClosestValueTo(exactPrice)
        val mostSuitableCityIndex = listOfAllPricesList.getMostSuitablePriceListIndex(closestPrice)
        val mostSuitableCity = filteredCities[mostSuitableCityIndex]
        return if (mostSuitableCity.isInBoundary(exactPrice, boundary)) mostSuitableCity else null
    }

    private fun List<Float>.getExactPrice(): Float? = if (this.isEmpty()) null else (this.maxOrNull()!! + this.minOrNull()!!) / 2
    private fun List<List<Float>>.getMostSuitablePriceListIndex(closestPrice: Float): Int =
        indexOfFirst { it.contains(closestPrice) }

    private fun List<CityEntity>.filteredByCountries(countries: List<String>): List<CityEntity> =
        filter { it.country.lowercase() in countries }

    private fun List<Float>.getClosestValueTo(exactPrice: Float): Float =
        fold(Float.MAX_VALUE to 0f) { preDifference, currentMealPrice ->
            val currDifference = abs(currentMealPrice - exactPrice)
            if (currDifference < preDifference.first) currDifference to currentMealPrice else preDifference
        }.second

    private fun CityEntity.isInBoundary(exactPrice: Float, boundary: Float): Boolean =
        mealsPrices.getNotNullPrices().any { abs(it - exactPrice) <= boundary }

    private fun MealsPrices.getNotNullPrices(): List<Float> = listOfNotNull(
        mealInexpensiveRestaurant,
        mealFor2PeopleMidRangeRestaurant?.div(2f),
        mealAtMcDonaldSOrEquivalent
    )
}
