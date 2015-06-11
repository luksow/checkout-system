package checkout.cart

import checkout.product.{Price, Product, ProductId}
import org.scalatest._

class OfferSpec extends FlatSpec with Matchers {
   val appleProduct = Product(ProductId("apple"), Price(BigDecimal("0.60")))
   val orangeProduct = Product(ProductId("orange"), Price(BigDecimal("0.25")))

   "GratisApple" should "discount correctly" in {
     GratisApple(Cart(List(appleProduct, orangeProduct))).discount should equal(BigDecimal(0))
     GratisApple(Cart(List(appleProduct, orangeProduct, appleProduct))).discount should equal(BigDecimal("0.60"))
     GratisApple(Cart(List(appleProduct, appleProduct, appleProduct))).discount should equal(BigDecimal("0.60"))
     GratisApple(Cart(List(appleProduct, appleProduct, appleProduct, appleProduct))).discount should equal(BigDecimal("1.20"))
   }

   "ThreeOrangesForTwo" should "discount correctly" in {
     ThreeOrangesForTwo(Cart(List(appleProduct, orangeProduct))).discount should equal(BigDecimal(0))
     ThreeOrangesForTwo(Cart(List(appleProduct, orangeProduct, orangeProduct))).discount should equal(BigDecimal(0))
     ThreeOrangesForTwo(Cart(List(appleProduct, orangeProduct, orangeProduct, orangeProduct))).discount should equal(BigDecimal("0.25"))
     ThreeOrangesForTwo(Cart(List(orangeProduct, orangeProduct, orangeProduct, orangeProduct))).discount should equal(BigDecimal("0.25"))
     ThreeOrangesForTwo(Cart(List(orangeProduct, orangeProduct, orangeProduct, orangeProduct, orangeProduct, orangeProduct))).discount should equal(BigDecimal("0.50"))
   }
 }
