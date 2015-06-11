package checkout.cart

case class Discount(discount: BigDecimal) extends AnyVal

trait Offer extends (Cart => Discount)

object GratisApple extends Offer {
  override def apply(cart: Cart): Discount = ???
}

object ThreeOrangesForTwo extends Offer {
  override def apply(cart: Cart): Discount = ???
}