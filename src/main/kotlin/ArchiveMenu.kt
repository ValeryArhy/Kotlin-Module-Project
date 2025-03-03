class ArchiveMenu : BaseMenu<Archive>("СПИСОК АРХИВОВ") {
    init {
        addMenuItem("Создать архив", ::createArchive)
    }

    override fun updateMenu() {
        menuItems.clear()
        addMenuItem("Создать архив", ::createArchive)
        items.forEach { archive ->
            addMenuItem(archive.name) { openArchive(archive) }
        }
    }

    private fun createArchive() {
        val name = readNonEmptyInput("Введите название архива: ")
        items.add(Archive(name))
        println("Архив '$name' создан!")
    }

    private fun openArchive(archive: Archive) {
        NoteMenu(archive).show()
    }
}
