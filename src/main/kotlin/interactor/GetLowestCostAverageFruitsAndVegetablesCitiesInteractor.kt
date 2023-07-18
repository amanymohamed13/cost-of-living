package interactor

import model.CityEntity
import kotlin.math.abs


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

        val avgFruitAndVegetablesCost = sumOfPrices / Count

        return Avarage(city, avgFruitAndVegetablesCost)
    }

    private fun Avarage(city: CityEntity, avgFruitAndVegetablesCost: Float): Float {
        val avgSalary = city.averageMonthlyNetSalaryAfterTax ?: 0f

        return if (avgSalary > 0.0) {
            avgSalary / avgFruitAndVegetablesCost
        } else {
            return abs(avgSalary / avgFruitAndVegetablesCost)
        }
    }

    companion object NumOfFruitsAndVegetables{
       const val Count=7
    }
}

