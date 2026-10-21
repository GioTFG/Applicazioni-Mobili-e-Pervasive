/*
 * -- Informazioni --
 * Scritto da: Ancora Giovanni
 * Matricola: 391457
 * il: 07/10/2026 (a casa)
 * IDE: IntelliJ IDEA
 * GitHub: https://github.com/GioTFG/Applicazioni-Mobili-e-Pervasive
 */

package lab2

data class Item (val name: String, val quantity: Int)

fun unitPriceCalculator(item: Item): Double = item.quantity * when (item.name) {
    "T-shirt" -> 15.99
    "Jeans" -> 29.99
    "Sneakers" -> 50.0
    "Backpack" -> 42.0
    else -> 0.0
}

fun calculateTotalPrice(items: List<Item>, unitPriceCalculator: (Item)->Double): Double = items.map { unitPriceCalculator(it) }.sum()

fun main() {
    val shoppingBag: List<Item> = listOf(
        Item("T-shirt", 2),
        Item("Jeans", 2),
        Item("Sneakers", 1),
        Item("Backpack", 1)
    )

    println(String.format("Total cost: %.2f", calculateTotalPrice(shoppingBag, ::unitPriceCalculator)))
}