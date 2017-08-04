package co.uk.visualobjects

class Food(val name: FoodNames.Value, val price: BigDecimal) {

}

// An Enumeration of all food names
object FoodNames extends Enumeration() {
  val Apple = Value("Apple")
  val Orange = Value("Orange")
  val Banana = Value("Banana")
}

// A Delicious Apple
object Apple extends Food(FoodNames.Apple, BigDecimal(0.60))

// A Juicy Orange
object Orange extends Food(FoodNames.Orange, BigDecimal(0.25))


