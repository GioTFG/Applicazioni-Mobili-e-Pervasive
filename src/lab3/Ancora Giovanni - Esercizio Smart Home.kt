/*
 * -- Informazioni --
 * Scritto da: Ancora Giovanni
 * Matricola: 391457
 * il: 20/10/2026-22/10/2026
 * IDE: IntelliJ IDEA Open Sourceq
 * GitHub: https://github.com/GioTFG/Applicazioni-Mobili-e-Pervasive
 */

package lab3

interface HouseComponent {
    val name: String
    fun turnOffAll()
    fun printAll(prefix: String = "")
    fun totalPower(): Double
}

class Room(override val name: String): HouseComponent {
    var components = mutableListOf<HouseComponent>()

    override fun turnOffAll() {
        for (c in components) c.turnOffAll()
    }

    override fun printAll(prefix: String) {
        println("${prefix}Room: $name (${totalPower()}W)")
        for (c in components) c.printAll("$prefix  ")
    }

    override fun totalPower(): Double = components.sumOf { it.totalPower() }
    fun addComponent(comp: HouseComponent) { components.add(comp) }
}

// Max Power in attributo a parte per ricordare in caso va riacceso il dispositivo
interface Device: HouseComponent {
    override val name: String
    val maxPower: Double
    var power: Double

    fun turnOn()
    fun turnOff()


    override fun printAll(prefix: String)
    override fun totalPower(): Double
}

interface Light: Device

class BasicLight(override val name: String): Light {
    override val maxPower: Double = 10.0
    override var power: Double = 0.0

    override fun totalPower(): Double = power
    override fun turnOff() {
        power = 0.0
    }

    override fun turnOffAll() { turnOff() }
    override fun turnOn() { power = maxPower }

    override fun printAll(prefix: String) {
        println("${prefix}Basic Light: $name (${totalPower()})")
    }
}

class ProLight(override val name: String): Light {
    override val maxPower: Double = 20.0
    override var power: Double = 0.0

    override fun printAll(prefix: String) {
        println("${prefix}Pro Light: $name")
    }

    override fun totalPower(): Double = power
    override fun turnOff() { power = 0.0 }
    override fun turnOffAll() { turnOff() }
    override fun turnOn() { power = maxPower }

    fun dim(percent: Double = 1.0) {
        power = percent * maxPower
    }
}

interface Sensor: Device {
    val sensitivity: Double
}
class BasicSensor(override val name: String): Sensor {
    override val maxPower: Double = 15.0
    override val sensitivity: Double = 50.0

    override var power: Double = 0.0
    override fun totalPower(): Double = power

    override fun turnOff() {
        power = 0.0
    }
    override fun turnOn() {
        power = maxPower
    }
    override fun turnOffAll() {
        turnOff()
    }
    override fun printAll(prefix: String) {
        println("${prefix}Basic Sensor: $name (${totalPower()})")
    }
}
class ProSensor(override val name: String): Sensor {
    override val maxPower: Double = 30.0
    override val sensitivity: Double = 100.0

    override var power: Double = 0.0
    override fun totalPower(): Double = power
    override fun turnOff() { power = 0.0 }
    override fun turnOffAll() { turnOff() }
    override fun turnOn() { power = maxPower }

    override fun printAll(prefix: String) {
        println("${prefix}Pro Sensor: $name (${totalPower()}W)")
    }
}

interface DeviceFactory {
    fun createLight(name: String): Light
    fun createSensor(name: String): Sensor
}

class ProDeviceFactory: DeviceFactory {
    override fun createLight(name: String) = ProLight(name)
    override fun createSensor(name: String) = ProSensor(name)
}

class BasicDeviceFactory: DeviceFactory {
    override fun createLight(name: String) = BasicLight(name)
    override fun createSensor(name: String) = BasicSensor(name)
}

class AcmeSmartPlugSDK {
    private var state = false
    fun switchState(on: Boolean) {
        state = on
        println("AcmeSmartPlugSDK: plug is now ${if (on) "ON"
        else "OFF"}")}
    fun isSwitchedOn(): Boolean = state
}

class AcmeSmartPlugSDKAdapter(override val name: String, private val smartPlugSDK: AcmeSmartPlugSDK): Device {
    override val maxPower: Double = 5.0

    override var power: Double = 0.0
    override fun turnOff() {
        smartPlugSDK.switchState(false)
        power = 0.0
    }
    override fun turnOn() {
        smartPlugSDK.switchState(true)
        power = maxPower
    }
    override fun turnOffAll() { turnOff() }

    override fun totalPower(): Double = power
    override fun printAll(prefix: String) {
        println("${prefix}Smart Plug: $name (${totalPower()}W)")
    }
}

interface ThermostatSubscriber {
    fun thermostatUpdate(temperature: Double)
}

class Thermostat(override val name: String): Device {
    override val maxPower: Double = 20.0

    override var power: Double = 0.0
    override fun turnOff() { power = 0.0 }
    override fun turnOn() { power = maxPower }
    override fun turnOffAll() { turnOff() }
    override fun totalPower(): Double = power

    override fun printAll(prefix: String) {
        println("${prefix}Thermostat: $name (${totalPower()}W)")
    }

    private val subscribers = mutableListOf<ThermostatSubscriber>()
    fun subscribe(subscriber: ThermostatSubscriber) { subscribers.add(subscriber) }
    fun unsubscribe(subscriber: ThermostatSubscriber) { subscribers.remove(subscriber) }
    private fun notifySubscribers() { for (s in subscribers) s.thermostatUpdate(temperature) }

    var temperature: Double = 20.0
        set(value) {
            field = value
            notifySubscribers()
        }
}

interface DeviceCreator {
    fun createDevice(name: String): Device
}
object ThermostatCreator: DeviceCreator {
    override fun createDevice(name: String) = Thermostat(name)
}

class Dashboard: ThermostatSubscriber {
    override fun thermostatUpdate(temperature: Double) {
        println("Dashboard: new temperature is $temperature.")
    }
}

fun main() {

}