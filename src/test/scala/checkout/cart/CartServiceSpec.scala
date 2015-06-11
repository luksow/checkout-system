package checkout.cart

import checkout.product.{Price, Product, ProductId}
import org.scalatest._

class CartServiceSpec extends FlatSpec with Matchers {
  val appleProduct = Product(ProductId("apple"), Price(BigDecimal("0.60")))
  val orangeProduct = Product(ProductId("orange"), Price(BigDecimal("0.25")))
  val cartService = new CartService
  val discountedCartService = new CartService(List(ThreeOrangesForTwo, GratisApple))

  "Empty cart" should "be worth 0" in {
    cartService.checkout(Cart.empty).total should equal(BigDecimal(0))
  }

  "Cart with products" should "calculate price correctly" in {
    val cartWithApple = Cart.empty.withProduct(appleProduct)
    cartService.checkout(cartWithApple).total should equal(BigDecimal("0.60"))

    val cartWithAppleAndOrange = Cart(Seq(appleProduct, orangeProduct))
    cartService.checkout(cartWithAppleAndOrange).total should equal(BigDecimal("0.85"))

    val cartWithManyProducts = Cart(Seq(appleProduct, appleProduct, orangeProduct, appleProduct))
    cartService.checkout(cartWithManyProducts).total should equal(BigDecimal("2.05"))
  }

  "Cart with products which service has discounts" should "calculate price correctly" in {
    discountedCartService.checkout(Cart(List(appleProduct, orangeProduct))).total should equal(BigDecimal("0.85"))
    discountedCartService.checkout(Cart(List(appleProduct, orangeProduct, appleProduct))).total should equal(BigDecimal("0.85"))
    discountedCartService.checkout(Cart(List(appleProduct, orangeProduct, orangeProduct, orangeProduct))).total should equal(BigDecimal("1.10"))
    discountedCartService.checkout(Cart(List(appleProduct, appleProduct, appleProduct, appleProduct,
      orangeProduct, orangeProduct, orangeProduct, orangeProduct, orangeProduct, orangeProduct))).total should equal(BigDecimal("2.20"))

  }
 }
