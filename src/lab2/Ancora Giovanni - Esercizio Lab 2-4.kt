/*
 * -- Informazioni --
 * Scritto da: Ancora Giovanni
 * Matricola: 391457
 * il: 07/10/2026 (a casa)
 * IDE: IntelliJ IDEA
 * GitHub: https://github.com/GioTFG/Applicazioni-Mobili-e-Pervasive
 */

package lab2

fun filterNumbers(list: List<Int>, predicate: (Int) -> Boolean): List<Int> = list.filter { predicate(it) }

fun isOdd(n: Int): Boolean = n % 2 != 0
fun isEven(n: Int): Boolean = n % 2 == 0

fun main() {
    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println("Starting list: $numbers")
    println("Even list: ${filterNumbers(numbers, ::isEven)}")
    println("Odd list: ${filterNumbers(numbers, ::isOdd)}")
}