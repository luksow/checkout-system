package checkout.cart

class CartService {
  import CartService._

  def checkout(cart: Cart): Total = Total(cart.products.map(_.price.price).fold(BigDecimal(0))(_ + _))
}

object CartService {
  case class Total(total: BigDecimal) extends AnyVal
}
