package interactor

import model.CityEntity

class GetTopCitiesForFashionableClothesInteractor(private val dataSource: CostOfLivingDataSource ) {
    fun execute(limit: Int): List<String> {
        return dataSource
            .getAllCitiesData()
            .filter(::isCityContainsAtLeastNonNullBrand)
            .sortedBy(::getAveragePriceForClothes)
            .takeIf { limit > 0 }
            ?.take(limit)
            ?.map { it.cityName }
            ?: emptyList()
    }

    private fun getAveragePriceForClothes(cityEntity: CityEntity): Float {
        var totalPrice = 0.0f
        cityEntity.clothesPrices.run {
            onePairOfMenLeatherBusinessShoes?.let {
                totalPrice += onePairOfMenLeatherBusinessShoes
            }
            oneSummerDressInAChainStoreZaraHAndM?.let {
                totalPrice += oneSummerDressInAChainStoreZaraHAndM
            }
            onePairOfNikeRunningShoesMidRange?.let {
                totalPrice += onePairOfNikeRunningShoesMidRange
            }
            onePairOfJeansLevis50oneOrSimilar?.let {
                totalPrice += onePairOfJeansLevis50oneOrSimilar
            }
        }

        return totalPrice
    }


    private fun isCityContainsAtLeastNonNullBrand(cityEntity: CityEntity): Boolean {
    return     (cityEntity.clothesPrices.onePairOfMenLeatherBusinessShoes!=null ||
            cityEntity.clothesPrices.oneSummerDressInAChainStoreZaraHAndM!=null ||
            cityEntity.clothesPrices.onePairOfNikeRunningShoesMidRange!=null ||
            cityEntity.clothesPrices.onePairOfJeansLevis50oneOrSimilar!=null )
    }
}