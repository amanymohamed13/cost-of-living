package interactor

import model.CityEntity


class GetLowestCostAverageFruitsAndVegetablesCitiesInteractor(
    private val dataSource: CostOfLivingDataSource
) {

    fun execute(limit: Int): List<String> {
        return dataSource.getAllCitiesData()
            .filter(::excludeNullSalariesAndLowQualityData)
            .sortedBy { calculateFruitAndVegetablesCostRatio(it) }
            .take(limit)
            .map { it.cityName }
    }

    private fun excludeNullSalariesAndLowQualityData(city: CityEntity): Boolean {
        return city.averageMonthlyNetSalaryAfterTax != null && city.dataQuality
    }


    private fun calculateFruitAndVegetablesCostRatio(city: CityEntity): Float {
        val fruitAndVegetablesPrices = city.fruitAndVegetablesPrices

        val sumOfPrices = (fruitAndVegetablesPrices.apples1kg ?: 0f) +
                (fruitAndVegetablesPrices.banana1kg ?: 0f) +
                (fruitAndVegetablesPrices.oranges1kg ?: 0f) +
                (fruitAndVegetablesPrices.tomato1kg ?: 0f) +
                (fruitAndVegetablesPrices.potato1kg ?: 0f) +
                (fruitAndVegetablesPrices.onion1kg ?: 0f) +
                (fruitAndVegetablesPrices.lettuceOneHead ?: 0f)

        val avgFruitAndVegetablesCost = sumOfPrices / 7

        val avgSalary = city.averageMonthlyNetSalaryAfterTax ?: 0f

        return if (avgSalary > 0.0) {
            avgFruitAndVegetablesCost / avgSalary
        } else {
            Float.MAX_VALUE
        }
    }
}

