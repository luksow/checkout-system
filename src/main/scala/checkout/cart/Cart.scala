package checkout.cart

import checkout.product.Product

object Cart {
  def empty: Cart = Cart(List())
}

case class Cart(products: Seq[Product]) {
  def withProduct(product: Product): Cart = Cart(products :+ product)
}