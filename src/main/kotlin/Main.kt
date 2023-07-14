import dataSource.CsvDataSource
import dataSource.utils.CsvParser
import interactor.CostOfLivingDataSource
import interactor.GetHighestSalaryAverageCitiesNamesInteractor
import interactor.SearchForSalary

fun main() {
    val csvParser = CsvParser()
    val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser)

    val getHighestSalaryAverageCities = GetHighestSalaryAverageCitiesNamesInteractor(dataSource)
    println(getHighestSalaryAverageCities.execute(limit = 10))
    printSeparationLine()

    val getAvargeSalaryForAcountry=SearchForSalary(dataSource)
   println(getAvargeSalaryForAcountry.execute("egypt"))


//    val getCityHasCheapestInternetConnectionInteractor = GetCityHasCheapestInternetConnectionInteractor(dataSource)
//    println(getCityHasCheapestInternetConnectionInteractor.execute())

}
private fun printSeparationLine(){
    print("\n_______________________________\n")
}

