package shoppingsystem

class Product(var name: String, var price: Double, var quantityPurchased: Int) {

    var total: Double = 0.0

    fun printProduct() {
        println("Name: $name")
        println("Price: $price")
        println("Quantity Purchased: $quantityPurchased")
        println("Total: $total")
    }

    fun calculateTotal() {
        total = price * quantityPurchased
    }
}

class Cart() {
    var products: MutableList<Product> = mutableListOf()

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun removeProduct(product: Product) {
        products.remove(product)
    }

    fun printCart() {
        for (product in products) {
            product.printProduct()
        }
    }

    fun calculateTotal(): Double {
        var total = 0.0
        for (product in products) {
            total += product.total
        }
        return total
    }

    fun calculateTotalWithDiscount(discountType: String): Double {
        val discount = Discount()
        var total = 0.0
        for (product in products) {
            total += product.price * product.quantityPurchased - discount.applyDiscount(product, discountType)
        }
        return total
    }
}

class Discount() {
    val buyOneGetOneFree = "OneFree"
    val percentOff = "20PercentOff"
    val none = "None"

    fun applyDiscount(product: Product, discountType: String): Double {
        var discount = 0.0
        when (discountType) {
            buyOneGetOneFree -> {
                val freeQuantity = product.quantityPurchased / 2
                discount = product.price * freeQuantity
            }
            percentOff -> {
                discount = product.price * product.quantityPurchased * 0.2
            }
            none -> {
                discount = 0.0
            }
        }
        return discount
    }
}

// Create a class named "Order" to generate an order summary, including the products purchased, their quantities, original prices, and any applied discounts.
