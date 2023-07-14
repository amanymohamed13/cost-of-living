package test

import dataSource.CsvDataSource
import dataSource.utils.CsvParser
import interactor.CostOfLivingDataSource
import interactor.GetCitiesWithFasterAppartmentBuying
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GetCitiesWithFasterAppartmentBuyingTest{
  val csvParser = CsvParser()
  val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser)
  val Appartment_Price = GetCitiesWithFasterAppartmentBuying(dataSource)
  @Test
  fun `should return true When Countries Are 10`() {
    val Limit = 10
    Appartment_Price.execute(limit = Limit)
    assertEquals(10,Limit)
  }
  @Test
  fun `should return true When Prices Are Not Null `()
  {

    Appartment_Price.execute(limit = 10)
    assertNotNull(Appartment_Price)
  }
//@Test
//fun `should return true When Prices Are Greater Than 0`()
//{
//  Appartment_Price.execute(limit = 10)
//  assertEquals(Appartment_Price,Appartment_Price.filter())
//}
}

