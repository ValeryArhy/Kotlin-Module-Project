import java.util.Scanner

abstract class BaseMenu<T>(private val title: String) {
    protected val scanner = Scanner(System.`in`)
    protected val items = mutableListOf<T>()
    val menuItems = mutableListOf<Pair<String, () -> Unit>>()

    protected fun addMenuItem(name: String, action: () -> Unit) {
        menuItems.add(name to action)
    }

    protected open fun updateMenu() {}

    protected fun readNonEmptyInput(prompt: String): String {
        while (true) {
            print(prompt)
            val input = scanner.nextLine()
            if (input.isNotEmpty()) return input
            println("Поле не может быть пустым!")
        }
    }

    fun show() {
        while (true) {
            updateMenu()
            println("\n$title")

            menuItems.forEachIndexed { index, (name, _) ->
                println("$index. $name")
            }
            println("${menuItems.size}. Выход")

            when (val choice = readChoice()) {
                menuItems.size -> break
                null -> continue
                else -> menuItems[choice].second()
            }
        }
    }

    private fun readChoice(): Int? {
        print("Введите номер: ")
        val input = scanner.nextLine()

        return try {
            val choice = input.toInt()
            when {
                choice !in 0..menuItems.size -> {
                    println("Ошибка: такой цифры нет введите от 0 до ${menuItems.size}")
                    null
                }

                else -> choice
            }
        } catch (e: NumberFormatException) {
            println("Ошибка: необходимо ввести цифру")
            null
        }
    }
}