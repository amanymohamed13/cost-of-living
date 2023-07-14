import dataSource.CsvDataSource
import dataSource.utils.CsvParser
import fakedata.FakeCarbonatedTaxesOnCokeCountriesData
import fakedata.FakeShortCarbonatedList
import interactor.*

fun main() {
    val csvParser = CsvParser()
    val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser)


//    val getCityHasCheapestInternetConnectionInteractor = GetCityHasCheapestInternetConnectionInteractor(dataSource)
//    println(getCityHasCheapestInternetConnectionInteractor.execute())

    printSeparationLine()

    val x= GetCountriesNamesWithHighestCokePrice(dataSource)
    println(x.execute())
    printSeparationLine()

    val getHighestSalaryAverageCitiesNamesInteractor= GetHighestSalaryAverageCitiesNamesInteractor(dataSource)
    println(getHighestSalaryAverageCitiesNamesInteractor.execute(4))
}
private fun printSeparationLine(){
    print("\n_______________________________\n")
}

