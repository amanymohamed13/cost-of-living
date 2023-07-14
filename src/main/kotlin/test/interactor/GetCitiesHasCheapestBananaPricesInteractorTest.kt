package interactor

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GetCitiesHasCheapestBananaPricesInteractorTest{


        @Test
        fun `should Return When Price Not Null`()
        {
            val banana1kg=13
            assertNotNull(banana1kg)
        }
        @Test
        fun `should  Return When Price Not Equal 0`()
        {
            val banana1kg=7
            assertNotEquals(0,banana1kg)
        }
        @Test
        fun `should Returne When Price Is Not Less Than 0`()
        {
            val banana1kg=7
            assertNotEquals(-7,banana1kg)
        }

}