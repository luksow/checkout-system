package checkout.cart

import checkout.product.Product

object Cart {
  def apply(): Cart = ???
}

case class Cart(products: Seq[Product]) {
  def withProduct(product: Product): Cart = ???
}