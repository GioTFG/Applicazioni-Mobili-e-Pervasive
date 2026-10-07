/*
 * -- Informazioni --
 * Scritto da: Ancora Giovanni
 * Matricola: 391457
 * il: 07/10/2026 (in classe)
 * IDE: IntelliJ IDEA
 * GitHub: https://github.com/GioTFG/Applicazioni-Mobili-e-Pervasive
 */

fun main() {
    val cat: List<String> = listOf("Dune", "1984", "Foundation", "Neuromancer", "Hyperion")

    println("--- Catalog ---")
    for ((i, v) in cat.withIndex()) {
        println("${i+1}: $v")
    }

    val events: List<String> = listOf("Dune", "1984", "Foundation", "Dune", "Hyperion", "1984")
    val borrowed: MutableSet<String> = mutableSetOf()
    val borrowCounter: MutableMap<String, Int> = mutableMapOf()

    println("\n--- Events ---")
    for (e in events) {
        when (e) {
            in borrowed -> {
                println("$e returned")
                borrowed.remove(e)
            }
            else -> {
                println("$e checked out")
                borrowed.add(e)

                borrowCounter[e] = borrowCounter.getOrDefault(e, 0) + 1
            }
        }
    }

    println("\nCurrently checked out: $borrowed")
    println("Times borrowed: $borrowCounter")
    println("Is 'Neuromancer' checked out? ${"Neuromancer" in borrowed}")

}