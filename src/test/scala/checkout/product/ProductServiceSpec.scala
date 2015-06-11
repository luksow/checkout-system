package checkout.product

import org.scalatest._

class ProductServiceSpec extends FlatSpec with Matchers {
  val appleProduct = Product(ProductId("apple"), Price(BigDecimal("0.60")))
  val orangeProduct = Product(ProductId("orange"), Price(BigDecimal("0.25")))
  val productRepository = new ProductRepository(List(appleProduct, orangeProduct))
  val productService = new ProductService(productRepository)

  "findByStringId" should "sanitize strings and return found products" in {
    productService.findByStringId("OrAnGe") should contain(orangeProduct)
    productService.findByStringId(" orange  ") should contain(orangeProduct)
    productService.findByStringId(" ApplE  ") should contain(appleProduct)
    productService.findByStringId("banana") shouldBe empty
  }
}
