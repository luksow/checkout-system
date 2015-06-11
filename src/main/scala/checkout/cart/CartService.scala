package checkout.cart

class CartService {
  import CartService._

  def checkout(cart: Cart): Total = ???
}

object CartService {
  case class Total(total: BigDecimal) extends AnyVal
}
