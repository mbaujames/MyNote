package com.joelkanyi.mynote.navigation

import androidx.navigation.NavHostController

const val ID = "id"
const val TITLE = "title"
const val CONTENT = "content"

sealed class Screen(val route: String) {

    object Splash : Screen(route = "splash_screen")
    object AddNote : Screen(route = "add_note_screen?title={$TITLE}&content={$CONTENT}&id={$ID}") {
        fun passNoteValues(title: String? = null, content: String? = null, id: Int? = null): String {
            return "add_note_screen?title=$title&content=$content&id=$id"
        }
    }

    object NotesScreen:Screen(route = "Notes_Screen")
    object AddNoteScreen :Screen(route = "add_notes_screen")
    //object :Screen(route = "Notes_Screen ")
    companion object {
        fun AddNoteScreen(navController: NavHostController) {

        }
    }
}
