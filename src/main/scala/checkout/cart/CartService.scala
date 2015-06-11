package checkout.cart

class CartService(offers: Seq[Offer]) {
  import CartService._

  def this() = this(Seq.empty)

  def checkout(cart: Cart): Total = {
    val subTotal = cart.products.map(_.price.price).fold(BigDecimal(0))(_ + _)
    val discount = offers.map(o => o(cart).discount).fold(BigDecimal(0))(_ + _)
    Total(subTotal - discount)
  }
}

object CartService {
  case class Total(total: BigDecimal) extends AnyVal
}
