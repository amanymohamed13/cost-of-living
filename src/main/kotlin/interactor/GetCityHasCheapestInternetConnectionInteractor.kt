package interactor

import model.CityEntity

class GetCityHasCheapestInternetConnectionInteractor(
    private val dataSource: CostOfLivingDataSource,
) {

    fun execute(limit:Int): List<String>{
        return dataSource
            .getAllCitiesData()
            .filter (::excludeNullSalariesAndLowQualityData)
            .sortedByDescending (::getAverageInternetConnection)
            .take(limit)
            .map { it.cityName }


    }

    private fun excludeNullSalariesAndLowQualityData(city: CityEntity): Boolean {
        return city.averageMonthlyNetSalaryAfterTax != null && city.dataQuality && city.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl!=null
    }
    private fun getAverageInternetConnection(cityEntity:CityEntity)= cityEntity.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl!! / cityEntity.averageMonthlyNetSalaryAfterTax!!



}