/*
 * -- Informazioni --
 * Scritto da: Ancora Giovanni
 * Matricola: 391457
 * il: 01/10/2026
 * IDE: Playground try.kotl.in
 * GitHub: https://github.com/GioTFG/Applicazioni-Mobili-e-Pervasive
 */

fun classify(n: Int): String = when {
    n < 0 -> "negative"
    n == 0 -> "zero"
    n < 10 -> "single digit"
    n < 100 -> "double digit"
    else -> "large"
}

fun sumOfDigits(n: Int): Int {
    var sum = 0
    var num = if (n >= 0) n else -n
    while (num != 0) {
        sum += num % 10
        num /= 10
    }
    return sum
}

fun printNumInfo(num: Int): Unit {
    println("$num: ${classify(num)} ${sumOfDigits(num)}")
}

fun main() {
    printNumInfo(0)
    printNumInfo(-42)

    println("Numeri da -20 a 20")
    for (n in -20..20 step 2) {
        printNumInfo(n)
    }

    for (n in 300 downTo 50 step 50) {
        printNumInfo(n)
    }
}