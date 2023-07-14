package interactor

import model.CityEntity
import java.util.*

class SearchForSalary(
    private val dataSource: CostOfLivingDataSource,

    )
{

    var country_after:String=" "
    fun execute(country:String): List<List<Any>> {
     country_after = correctTheCountryName(countryName = country)
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
        return city.country == country_after
    }

    private fun correctTheCountryName(countryName: String):String{

       return countryName[0].uppercaseChar()+""+countryName.slice(1 until (countryName.length-1)).lowercase()
//        for (i in countryName.indices-1){
//            if (i == 0)
//            {
//
//                    countryName[i].uppercaseChar()
//
//            }
//            else{
//
//                    countryName.lowercase()
//
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