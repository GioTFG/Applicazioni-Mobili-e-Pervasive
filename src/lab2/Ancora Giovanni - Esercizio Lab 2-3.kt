/*
 * -- Informazioni --
 * Scritto da: Ancora Giovanni
 * Matricola: 391457
 * il: 07/10/2026 (in classe e a casa)
 * IDE: IntelliJ IDEA
 * GitHub: https://github.com/GioTFG/Applicazioni-Mobili-e-Pervasive
 */

package lab2

class Employee (val name: String, val department: String, val salary: Int)

fun totalBy(employees: List<Employee>, selector: (Employee) -> Int): Int {
    var sum: Int = 0
    for (e in employees) sum += selector(e)
    return sum
}

fun printByDepartment(employees: List<Employee>, toPrint: (Employee) -> String): Unit {
    for ((dep, empList) in employees.groupBy { it.department }) {
        println("\tDepartment: $dep")
        for (e in empList) println("\t\t${toPrint(e)}")
    }
}
fun printByParameter(employees: List<Employee>, toPrint: (Employee) -> String) {
    print("[ ")
    for(e in employees) print("${toPrint(e)}, ")
    print("]")
}

fun printByMap(employees: List<Employee>, mapping: (Employee) -> String) {
    for (e in employees) println("\t${e.name} -> ${mapping(e)}")
}

fun printMap(m: MutableMap<String, Int>) {
    for ((k,v) in m) println("\t$k -> $v")
}

fun main() {
    val l: List<Employee> = listOf(
        Employee("Giovanni", "Dep1", 5000),
        Employee("Giorgio", "Dep2", 4000),
        Employee("Marta", "Dep1", 6000),
        Employee("Harold", "Dep2", 4500),
        Employee("Leonarda", "Dep1", 5500)
    )

    print("Employees: ")
    printByParameter(l) {it.name}
    println()

    val threshold: Int = 5000
    print("Exceeding $threshold: ")
    printByParameter(l.filter { it.salary > threshold }) {it.name}
    println()

    println("Bonuses (10%):")
    printByMap(l) {(it.salary * 10 / 100).toString()}

    println("Employees by department:")
    printByDepartment(l) {it.name}

    val sumByDep: MutableMap<String, Int> = mutableMapOf()
    val departments: Set<String> = l.map { it.department }.toSet()

    for (d in departments) sumByDep[d] = totalBy(l.filter { it.department == d }, { it.salary })

    println("Salary by department:")
    printMap(sumByDep)
}