/*
 * -- Informazioni --
 * Scritto da: Ancora Giovanni
 * Matricola: 391457
 * il: 01/10/2026
 * IDE: IntelliJ IDEA
 * GitHub: https://github.com/GioTFG/Applicazioni-Mobili-e-Pervasive
 */

fun checkString (s: String?): Boolean {
    val allowedLetters: Set<Char> = setOf('A', 'C', 'T', 'G')

    if (s == null) return false
    for (c: Char in s) {
        if (!allowedLetters.contains(c)) return false
    }
    return true
}

fun main() {
    print("Insert first string: ")
    val s1: String? = readlnOrNull() // IntelliJ dà il warning che readLine() è deprecato
    print("Insert second string: ")
    val s2: String? = readlnOrNull()

    if (!checkString(s1)) {
        println("First string is not allowed")
        return
    }
    if (!checkString(s2)) {
        println("Second string is not allowed")
        return
    }

    if (s1!!.length != s2!!.length) {
        println("Hamming Distance not possible")
        return
    }

    var distance = 0
    for (i in 0 ..< s1.length) {
        if (s1[i] != s2[i]) distance++
    }

    println("Hamming distance: $distance")
}