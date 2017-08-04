package co.uk.visualobjects

class StoreCheckout {

  def scan(product: Food): Option[BigDecimal] = product.name match {
    case FoodNames.Apple => Some(product.price)
    case FoodNames.Orange => Some(product.price)
    case _ => None
  }

  def scan(products: Seq[Food]): BigDecimal = {
    products.map(scan).flatten.fold(BigDecimal(0))(_ + _)
  }
}
