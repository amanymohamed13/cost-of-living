package interactor

import model.CityEntity
import java.util.*

class SearchForSalary(
    private val dataSource: CostOfLivingDataSource,
   private var Name:String
){
    init {
        this.Name=correctTheCountryName(Name)
    }
    fun execute(limit:Int): List<List<Any>> {
//      val Name = correctTheCountryName(countryName)
        return listOf(dataSource
            .getAllCitiesData()
            .filter (::excludeNullSalariesAndLowQualityData)
            .filter (::excludeOtherCountries )
            .map {
                Pair(it.cityName,it.averageMonthlyNetSalaryAfterTax).toList()
            })




    }



    private fun excludeNullSalariesAndLowQualityData(city: CityEntity): Boolean {
        return city.averageMonthlyNetSalaryAfterTax != null && city.dataQuality
    }
    private fun excludeOtherCountries(city: CityEntity): Boolean {
        return city.country == Name
    }

    private fun correctTheCountryName(countryName: String):String{
//        for (i in countryName.indices-1){
//            if (i == 0)
//            {
//                if (countryName[i]>='a' && countryName[i] <= 'z'){
//                    countryName[i].uppercaseChar()
//                }
//            }
//            else{
//                if (countryName[i]>='A' && countryName[i] <= 'Z'){
//                    countryName.lowercase()
//                }
//            }
//        }
//        return countryName


        return countryName.replaceFirstChar {
            if (it.isLowerCase())
                it.titlecase(Locale.getDefault())
            else
                it.toString()
        }

    }

}