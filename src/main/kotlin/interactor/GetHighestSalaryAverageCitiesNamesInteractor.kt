package interactor

import model.CityEntity
import model.FoodPrices
import model.ServicesPrices

class GetHighestSalaryAverageCitiesNamesInteractor (
    private val dataSource: CostOfLivingDataSource
) {


    fun execute(limit: Int): List<String> {
        return dataSource.getAllCitiesData()
            .filter(::excludeNullSalariesAndLowQualityData)
            .filter(::checkAffordability)
            .sortedByDescending { it.averageMonthlyNetSalaryAfterTax }
            .take(limit)
            .map { it.cityName }.toList()
    }

    private fun excludeNullSalariesAndLowQualityData(city: CityEntity): Boolean {
        return city.averageMonthlyNetSalaryAfterTax != null && city.dataQuality
    }

    private fun checkAffordability(city: CityEntity): Boolean {
        val totalExpenses = calculateTotalExpenses(city)
        val remainingBudget = (city.averageMonthlyNetSalaryAfterTax!! *2 )- totalExpenses

        // Consider only cities where the remaining budget is positive
        return remainingBudget > 0
    }

    private fun calculateTotalExpenses(city: CityEntity): Float {
        val foodExpenses = calculateFoodExpenses(city.foodPrices)
        val otherExpenses = calculateOtherExpenses(city.servicesPrices)

        return foodExpenses + otherExpenses
    }

    private fun calculateFoodExpenses(foodPrices: FoodPrices): Float {
        val breadCost = foodPrices.loafOfFreshWhiteBread500g ?: 0f
        val cheeseCost = foodPrices.localCheese1kg ?: 0f
        val beefCost = foodPrices.beefRound1kgOrEquivalentBackLegRedMeat ?: 0f
        val chickenCost = foodPrices.chickenFillets1kg ?: 0f
        val riceCost = foodPrices.riceWhite1kg ?: 0f

        return (breadCost * 15) + cheeseCost + (beefCost * 4) + (chickenCost * 10) + (riceCost * 2)
    }

    private fun calculateOtherExpenses(servicesPrices: ServicesPrices): Float {
        // Consider only the limit of $250 for other expenses
        val limit = 250.0f

        val totalOtherExpenses = listOf(
            servicesPrices.basicElectricityHeatingCoolingWaterGarbageFor85m2Apartment ?: 0f,
            servicesPrices.oneMinOfPrepaidMobileTariffLocalNoDiscountsOrPlans ?: 0f,
            servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl ?: 0f,
            servicesPrices.fitnessClubMonthlyFeeForOneAdult ?: 0f,
            servicesPrices.tennisCourtRentOneHourOnWeekend ?: 0f,
            servicesPrices.cinemaInternationalReleaseOneSeat ?: 0f,
            servicesPrices.preschoolOrKindergartenFullDayPrivateMonthlyForOneChild ?: 0f,
            servicesPrices.internationalPrimarySchoolYearlyForOneChild ?: 0f
        ).sum()

        return if (totalOtherExpenses <= limit) totalOtherExpenses else Float.MAX_VALUE
    }

    private fun calculateOtherExpenses(): Float {
        // Consider only the limit of $250 for other expenses
        val limit = 250.0f

        return limit
    }

}