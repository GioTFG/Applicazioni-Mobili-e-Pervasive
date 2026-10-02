/*
 * -- Informazioni --
 * Scritto da: Ancora Giovanni
 * Matricola: 391457
 * il: 01/10/2026 - 02/10/2026
 * IDE: IntelliJ IDEA
 * GitHub: https://github.com/GioTFG/Applicazioni-Mobili-e-Pervasive
 */

val allowedLetters = setOf<Char>(
    'A', 'B', 'C', 'D', 'E', 'F',
    'G', 'H', 'I', 'J', 'K', 'L',
    'M', 'N', 'O', 'P', 'Q', 'R',
    'S', 'T', 'U', 'V', 'W', 'X',
    'Y', 'Z'
)

val allowedWords = setOf<String>(
    "PAROLA", "CIAO", "PARMA", "UNI", "WOW", "BELLO",
    "SET", "DENTE", "GIOCO", "APP", "JEEP", "LISTA",
    "MAPPA", "VETTORE", "GOLF", "CALCIO", "SCI", "PISA",
    "PISOLO", "SONNO", "LETTO", "LIBRO", "CALDO", "KOALA", "BOH"
)

fun generateLetters(): List<Char> {
    val l: MutableList<Char> = mutableListOf()
    for (i in 1..8) l.addLast(allowedLetters.random())
    return l
}

fun isWordAllowed(word: String): Boolean = allowedWords.contains(word.uppercase())
fun enoughLetters(word: String, letters: List<Char>): Boolean {
    val remaining: MutableList<Char> = letters.toMutableList()

    for (c: Char in word) {
        if (remaining.contains(c.uppercaseChar())) remaining.remove(c)
        else return false
    }
    return true
}

fun getLetterScore(c: Char): Int = when (c) {
    'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' -> 1
    'D', 'G' -> 2
    'B', 'C', 'M', 'P' -> 3
    'F', 'H', 'V', 'W', 'Y' -> 4
    'K' -> 5
    'J', 'X' -> 8
    'Q', 'Z' -> 10
    else -> 0
}
fun getWordScore(word: String): Int {
    var sum: Int = 0
    for (c: Char in word) sum += getLetterScore(c)
    return sum
}

fun main() {
    val letters: List<Char> = generateLetters()

    println("Letters available: $letters")

    var word: String?
    do {
        print("Insert a word: ")
        word = readlnOrNull()
        println("Word: '$word'")
    } while (word == null || !isWordAllowed(word) || !enoughLetters(word, letters))

    val score: Int = getWordScore(word)
    println("Word score: $score")
}