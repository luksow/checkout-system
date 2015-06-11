package checkout.cart

import checkout.product.{Price, Product, ProductId, ProductRepository}
import org.scalatest._

class CartSpec extends FlatSpec with Matchers {
  val appleProduct = Product(ProductId("apple"), Price(BigDecimal("0.60")))
  val orangeProduct = Product(ProductId("orange"), Price(BigDecimal("0.25")))

  "cart" should "be empty after created" in {
    Cart().products shouldBe empty
  }

  it should "allow consumer to add new products" in {
    val emptyCart = Cart()
    val cartWithApple = emptyCart.withProduct(appleProduct)
    cartWithApple.products should contain(appleProduct)
    val cartWithAnotherApple = cartWithApple.withProduct(appleProduct)
    cartWithAnotherApple.products shouldEqual Seq(appleProduct, appleProduct)
  }
}
