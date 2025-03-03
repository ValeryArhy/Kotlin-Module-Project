class NoteMenu(private val archive: Archive) : BaseMenu<Note>("АРХИВ: ${archive.name}") {
    init {
        addMenuItem("Создать заметку", ::createNote)
    }

    override fun updateMenu() {
        menuItems.clear()
        addMenuItem("Создать заметку", ::createNote)
        archive.notes.forEach { note ->
            addMenuItem(note.title) { showNote(note) }
        }
    }

    private fun createNote() {
        val title = readNonEmptyInput("Введите заголовок: ")
        val content = readNonEmptyInput("Введите текст: ")

        archive.notes.add(Note(title, content))
        println("Заметка '$title' создана!")
    }

    private fun showNote(note: Note) {
        println("Заметка\n${note.title}")
        println("-".repeat(30))
        println("Текст заметки\n${note.content}")
        print("\nНажмите Enter для возврата...")
        scanner.nextLine()
    }
}
