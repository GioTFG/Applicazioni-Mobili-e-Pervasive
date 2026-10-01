#!/usr/bin/env kotlin

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
    val s1: String? = readLine()
    print("Insert second string: ")
    val s2: String? = readLine()

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
    for (i in 0 until s1.length) {
        if (s1[i] != s2[i]) distance++
    }

    println("Hamming distance: $distance")
}