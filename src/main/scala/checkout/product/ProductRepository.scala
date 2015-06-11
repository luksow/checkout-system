package checkout.product

class ProductRepository(private val products: Seq[Product]) {
  def findById(id: ProductId): Option[Product] = ???
}