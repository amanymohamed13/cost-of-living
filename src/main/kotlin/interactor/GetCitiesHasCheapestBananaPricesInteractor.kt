package interactor
import model.CityEntity



class GetCitiesHasCheapestBananaPricesInteractor(
private val dataSource: CostOfLivingDataSource,
)
{
    fun execute(limit: Int): List<String> {
        return dataSource
            .getAllCitiesData()
            .filter(::excludeNullPricesAndLowQualityData)
            .sortedBy (::getBananaPrices )
            .take(limit)
            .map { it.cityName }
    }
    private fun excludeNullPricesAndLowQualityData(city: CityEntity): Boolean {
        return city.fruitAndVegetablesPrices.banana1kg != null && city.dataQuality
    }
    private fun getBananaPrices(city:CityEntity): Float
    {
        val BananaPrice= city.fruitAndVegetablesPrices.banana1kg
        return if (BananaPrice!!> 0.0F)
              BananaPrice
        else
            return Float.MAX_VALUE
    }
}
