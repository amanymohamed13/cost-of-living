import dataSource.CsvDataSource
import dataSource.utils.CsvParser
import interactor.*


fun main() {
    val csvParser = CsvParser()
    val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser)

    val getHighestSalaryAverageCities = GetHighestSalaryAverageCitiesNamesInteractor(dataSource)
    println(getHighestSalaryAverageCities.execute(limit = 1))
    printSeparationLine()

    val getAvargeSalaryForAcountry = GetAvarageSalaryForEachCityInCountry(dataSource)
    println(getAvargeSalaryForAcountry.execute("egypt"))
    printSeparationLine()

    val getcitiesHasCheapestBananaPricesInteractor = GetCitiesHasCheapestBananaPricesInteractor(dataSource)
    println(getcitiesHasCheapestBananaPricesInteractor.execute(10))
    printSeparationLine()

     val getCitiesWithFasterAppartmentBuying=GetCitiesWithFasterAppartmentBuying(dataSource)
     println(getCitiesWithFasterAppartmentBuying.execute(5))
     printSeparationLine()


    val getCityHasCheapestInternetConnectionInteractor = GetCityHasCheapestInternetConnectionInteractor(dataSource)
    println(getCityHasCheapestInternetConnectionInteractor.execute(10))

}

private fun printSeparationLine() {
    print("\n_______________________________\n")
}

