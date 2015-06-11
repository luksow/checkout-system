package checkout.product

class ProductService(private val productRepository: ProductRepository) {
  def findByStringId(id: String): Option[Product] = productRepository.findById(ProductId(id.toLowerCase.trim))
}
