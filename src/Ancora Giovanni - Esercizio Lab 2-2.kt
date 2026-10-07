/*
 * -- Informazioni --
 * Scritto da: Ancora Giovanni
 * Matricola: 391457
 * il: 07/10/2026 (in classe)
 * IDE: IntelliJ IDEA
 * GitHub: https://github.com/GioTFG/Applicazioni-Mobili-e-Pervasive
 */

fun min(a: Int, b: Int): Int = if (a < b) a else b
fun max(a: Int, b: Int): Int = if (a > b) a else b
fun clamp(v: Int, lBound: Int, uBound: Int): Int = min(max(v, lBound), uBound)

class Thermostat (val minTemp: Int = 10, val maxTemp: Int = 30) {
    var temperature: Int = minTemp
        set(value) {
            field = clamp(value, minTemp, maxTemp)
        }

    val isAtLimit: Boolean
        get() = temperature == minTemp || temperature == maxTemp

    fun heat(degrees: Int): Unit {
        this.temperature += degrees
    }
    fun cool(degrees: Int): Unit {
        this.temperature -= degrees
    }
}

fun main() {
    val t: Thermostat = Thermostat()

    println("Temperatura: ${t.temperature} Limite? ${t.isAtLimit}")
    t.heat(100)
    println("Temperatura: ${t.temperature} Limite? ${t.isAtLimit}")
    t.cool(10)
    println("Temperatura: ${t.temperature} Limite? ${t.isAtLimit}")
    t.cool(1000)
    println("Temperatura: ${t.temperature} Limite? ${t.isAtLimit}")

}