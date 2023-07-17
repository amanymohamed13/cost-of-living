import interactor.CostOfLivingDataSource


import model.CityEntity

class GetAvarageSalaryForEachCityInCountry(
    private val dataSource: CostOfLivingDataSource,

    ) {

    var country_after: String = " "
    fun execute(country: String): List<Pair<String, Float>>? {
        country_after = correctTheCountryName(countryName = country)
        return dataSource
            .getAllCitiesData().filter { excludeNullSalariesAndLowQualityData(it) && it.country.equals(country, true) }
            .map {
                it.cityName to it.averageMonthlyNetSalaryAfterTax!!
            }


    }

     fun excludeNullSalariesAndLowQualityData(city: CityEntity): Boolean {
        return city.averageMonthlyNetSalaryAfterTax != null && city.dataQuality
    }

     fun correctTheCountryName(countryName: String): String {

        return countryName[0].uppercaseChar() + "" + countryName.slice(1 until (countryName.length - 1)).lowercase()


    }
}
//
//}
//class GetCitiesNamesAndAverageSalariesInCountry(
//    private val dataSource: CostOfLivingDataSource,
//) {
//
//    fun execute(country: String): List<Pair<String, Float>> {
//        return dataSource
//            .getAllCitiesData()
//            .filter { it.isCityNamesMatch(country) && it.isHighQualityDataAndNotNullSalaries() }
//            .map { it.cityName to it.averageMonthlyNetSalaryAfterTax!! }
//            .takeIf { it.isNotEmpty() } ?: throw Exception("wrong name")
//    }
//
//
//    private fun CityEntity.isCityNamesMatch(country: String) =
//        this.country.lowercase() == country.lowercase()
//
//    private fun CityEntity.isHighQualityDataAndNotNullSalaries() =
//        averageMonthlyNetSalaryAfterTax != null && dataQuality
//
//
//}
