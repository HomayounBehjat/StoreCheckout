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

  test("The price of three apples and one orange is 1.45") {
    val apple = new Food(FoodNames.Apple, BigDecimal(0.60))
    val orange = new Food(FoodNames.Orange, BigDecimal(0.25))

    // An Apple And An Orange cost 85 pence
    assert((storeCheckout scan List(apple, apple, orange, apple)) == BigDecimal(1.45))
  }

  test("The price of two apples and one orange is 0.85 pence since one apple is free") {
    val apple = new Food(FoodNames.Apple, BigDecimal(0.60))
    val orange = new Food(FoodNames.Apple, BigDecimal(0.25))

    // An Apple And An Orange cost 85 pence
    assert((storeCheckout scan List(apple, apple, orange)) == BigDecimal(0.85))
  }

  test("The price of two apples should be 0.60 pence since the second apple should be free of charge") {

    val apple = new Food(FoodNames.Apple, BigDecimal(0.60))

    assert((storeCheckout scan List(apple, apple)) == BigDecimal("0.60"))
  }

  test("You Should get one free if you buy three oranges") {

    val orange = new Food(FoodNames.Orange, BigDecimal(0.25))

    assert((storeCheckout scan List(orange, orange, orange)) == BigDecimal(0.50))
  }

  test("You should get one apple and one orange free") {
    val apple = new Food(FoodNames.Apple, BigDecimal(0.60))
    val orange = new Food(FoodNames.Orange, BigDecimal(0.25))

    assert((storeCheckout scan List(apple, orange, orange, apple, orange, apple, orange)) == BigDecimal(1.95))
  }

}
