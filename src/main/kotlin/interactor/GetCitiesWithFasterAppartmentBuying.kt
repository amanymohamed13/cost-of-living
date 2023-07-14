package interactor

import model.CityEntity
import kotlin.math.min

class GetCitiesWithFasterAppartmentBuying (
        private val dataSource: CostOfLivingDataSource,
) {

    fun execute(limit: Int=10) : List<Pair<String, Float>> {
       return dataSource
            .getAllCitiesData()
            .filter(::excludeNullPricesAndLowQualityData)
            .sortedBy { getAppartmentPrice(it) }
            .take(limit)
            .map { (it.cityName to (it.let { min(
                it.realEstatesPrices.apartmentOneBedroomInCityCentre!!
                , it.realEstatesPrices.apartmentOneBedroomOutsideOfCentre!! )/ it.averageMonthlyNetSalaryAfterTax!!}))
            }
    }

    private fun excludeNullPricesAndLowQualityData(city: CityEntity): Boolean {
        return with(city.realEstatesPrices) {
            apartmentOneBedroomInCityCentre != null &&

                    city.dataQuality
        }
    }

    private fun getAppartmentPrice(city: CityEntity): Float {
        return min(
            city.realEstatesPrices.apartmentOneBedroomInCityCentre!!,
            city.realEstatesPrices.apartmentOneBedroomOutsideOfCentre!!
        )
    }

}


