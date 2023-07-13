import dataSource.CsvDataSource
import dataSource.utils.CsvParser
import interactor.CostOfLivingDataSource
import interactor.GetCityHasCheapestInternetConnectionInteractor
import interactor.GetCountriesNamesWithHighestCokePrice

fun main() {
    val csvParser = CsvParser()
    val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser)


    val getCityHasCheapestInternetConnectionInteractor = GetCityHasCheapestInternetConnectionInteractor(dataSource)
    println(getCityHasCheapestInternetConnectionInteractor.execute())

    printSeparationLine()

    val x= GetCountriesNamesWithHighestCokePrice(dataSource)
    println(x.execute())

    printSeparationLine()

}
private fun printSeparationLine(){
    print("\n_______________________________\n")
}

