val allowedLetters = setOf<Char>(
    'A', 'B', 'C', 'D', 'E', 'F',
    'G', 'H', 'I', 'J', 'K', 'L',
    'M', 'N', 'O', 'P', 'Q', 'R',
    'S', 'T', 'U', 'V', 'W', 'X',
    'Y', 'Z'
)

val allowedWords = setOf<String>(
    "parola", "ciao", "bob", "abete", "imola"
)

fun generateLetters(): List<Char> {
    val l: MutableList<Char> = mutableListOf()
    for (i in 1..8) l.addLast(allowedLetters.random())
    return l
}

fun isWordAllowed(word: String): Boolean = allowedWords.contains(word)
fun enoughLetters(word: String, letters: List<Char>): Boolean {
    var remaining: MutableList<Char> = letters.toMutableList()

    for (c: Char in word) {
        if (word.contains(c)) remaining.remove(c)
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

    println("Le tue lettere: $letters")
}