package co.uk.visualobjects

class StoreCheckout {

  def scan(product: Food): Option[BigDecimal] = product.name match {
    case FoodNames.Apple => Some(product.price)
    case FoodNames.Orange => Some(product.price)
    case _ => None
  }

  def scan(products: Seq[Food]): BigDecimal = {
    products.map(scan).flatten.fold(BigDecimal(0))(_ + _)  -
      // Deduct discounts for multiples of 2 Apples
      discount(products, 2,  new Food(FoodNames.Apple, BigDecimal(0.60))) -
      // Deduct discounts for multiples of 3 Oranges
      discount(products, 3, new Food(FoodNames.Orange, BigDecimal(0.25)))
  }

  def discount(products: Seq[Food], discountedQuantity: Int, discountedProduct: Food) =
    products.count(_.name == discountedProduct.name) / discountedQuantity * scan(discountedProduct).get
}
