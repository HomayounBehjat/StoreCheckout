package co.uk.visualobjects


import org.scalatest.{Tag, FunSuite}

/**
  * Created by Homayoun on 03/08/2017.
  */
class StoreCheckoutTestSuit extends FunSuite {

  val storeCheckout = new StoreCheckout

  test("You cannot scan a Banana") {

    val banana:Food = new Food(FoodNames.Banana, BigDecimal(0.55))

    // This amount now includes a service charge of 20%
    assert(storeCheckout scan banana isEmpty)
  }

  test("You can scan an apple") {

    val apple:Food = new Food(FoodNames.Apple, BigDecimal(0.65))

    // This amount should not includes any service charges
    assert(storeCheckout scan apple nonEmpty)
  }

  test("You can scan an orange") {

    val orange:Food = new Food(FoodNames.Apple, BigDecimal(0.25))

    assert(storeCheckout scan orange nonEmpty)
  }

  test("The price of an apple is 60p") {
    val apple:Food = new Food(FoodNames.Apple, BigDecimal(0.60))

    // One Apple Costs 60p
    assert((storeCheckout scan apple).get == BigDecimal(0.60))
  }

  test("The price of an apple and an orange is 85p") {
    val apple1:Food = new Food(FoodNames.Apple, BigDecimal(0.60))
    val apple2:Food = new Food(FoodNames.Apple, BigDecimal(0.60))
    val orange:Food = new Food(FoodNames.Apple, BigDecimal(0.25))
    val apple3:Food = new Food(FoodNames.Apple, BigDecimal(0.60))

    // An Apple And An Orange cost 85 pence
    assert((storeCheckout scan List(apple1, apple2, orange, apple3)) == BigDecimal(2.05))
  }



}
