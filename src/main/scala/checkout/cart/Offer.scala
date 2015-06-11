package checkout.cart

import checkout.product.ProductId

case class Discount(discount: BigDecimal) extends AnyVal

trait Offer extends (Cart => Discount)

object GratisApple extends Offer {
  override def apply(cart: Cart): Discount = {
    cart.products.filter(_.id == ProductId("apple")).toList match {
      case apples @ (x :: _) => Discount(x.price.price * (apples.length / 2))
      case List() => Discount(BigDecimal(0))
    }
  }
}

object ThreeOrangesForTwo extends Offer {
  override def apply(cart: Cart): Discount = {
    cart.products.filter(_.id == ProductId("orange")).toList match {
      case oranges @ (x :: _) => Discount(x.price.price * (oranges.length / 3))
      case List() => Discount(BigDecimal(0))
    }
  }
}