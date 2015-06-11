package checkout.product

import org.scalatest._

class ProductRepositorySpec extends FlatSpec with Matchers {
  val appleProduct = Product(ProductId("apple"), Price(BigDecimal("0.60")))
  val orangeProduct = Product(ProductId("orange"), Price(BigDecimal("0.25")))
  val productRepository = new ProductRepository(List(appleProduct, orangeProduct))

  "findById" should "return product if it's in database" in {
    productRepository.findById(ProductId("apple")) should contain(appleProduct)
    productRepository.findById(ProductId("orange")) should contain(orangeProduct)
  }

  it should "return empty value if product isn't in database" in {
    productRepository.findById(ProductId("banana")) shouldBe empty
  }
}
