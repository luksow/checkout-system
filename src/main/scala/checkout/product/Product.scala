package checkout.product

case class ProductId(id: String) extends AnyVal
case class Price(price: BigDecimal) extends AnyVal
case class Product(id: ProductId, price: Price)
