package checkout

import checkout.cart.{CartService, Cart}
import checkout.product._
import scala.annotation.tailrec

object Till {
  def nextCode: Option[String] = {
    val line = scala.io.StdIn.readLine()
    if (line.trim.isEmpty) None else Option(line)
  }
}

object Main extends App {
  private val appleProduct = Product(ProductId("apple"), Price(BigDecimal("0.60")))
  private val orangeProduct = Product(ProductId("orange"), Price(BigDecimal("0.25")))
  private val productRepository = new ProductRepository(List(appleProduct, orangeProduct))
  private val productService = new ProductService(productRepository)
  private val cartService = new CartService

  @tailrec
  private def processInput(nextInput: Option[String], cart: Cart): Unit = {
    nextInput match {
      case Some(input) =>
        val newCart = productService.findByStringId(input) match {
          case Some(product) => cart.withProduct(product)
          case None =>
            println(s"$input is an invalid product code")
            cart
        }

        val subTotal = cartService.checkout(newCart)
        println(s"Your cart is currently worth ${subTotal.total}")

        processInput(Till.nextCode, newCart)
      case None =>
        val total = cartService.checkout(cart)
        println(s"Your total for the order is ${total.total}")
    }
  }

  processInput(Till.nextCode, Cart())
}
