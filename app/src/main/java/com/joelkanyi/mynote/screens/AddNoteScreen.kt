package com.joelkanyi.mynote.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joelkanyi.mynote.data.NoteDatabase
import com.joelkanyi.mynote.data.NoteEntity
import com.joelkanyi.mynote.navigation.Screen

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TypeNotesScreen(
    title: String = "",
    content: String = "",
    id: Int? = null,
    navController: NavController,)
{
    var noteTitle by remember {
        mutableStateOf(title)
    }

    var noteContent by remember {
        mutableStateOf(content)
    }
    val context = LocalContext.current
    val database = NoteDatabase.getDatabase(context)
    val noteDao = database.noteDao()

    Scaffold(
        backgroundColor = Color.Black,
        topBar = {
            TopAppBar(
                backgroundColor = Color.Black,
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .size(45.dp),
                            onClick = {
                                navController.popBackStack()

                            })
                        {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null,
                                tint = Color.White
                            )

                        }

                        IconButton(
                            modifier = Modifier
                                .padding(end = 40.dp)
                                .size(35.dp),
                            onClick = {

                                // If title is not empty, this means it is a note update
                                if (title != "") {
                                    val note = NoteEntity(
                                        id = id,
                                        title = noteTitle,
                                        content = noteContent
                                    )
                                    noteDao.updateANote(note)

                                } else { // This means it is a new note
                                    val note = NoteEntity(
                                        title = noteTitle,
                                        content = noteContent,
                                    )
                                    noteDao.insertNote(note)
                                }

                                navController.navigate(Screen.NotesScreen.route)

                            }
                        )
                        {
                            Icon(
                                imageVector = Icons.Default.Done,
                                contentDescription = null,
                                tint = Color.White)
                        }
                    }
//                    Text(text = "Add Note")
                }
            )
        }

    )
    {

        Column(modifier = Modifier.padding(16.dp)) {

            TextField(
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.White, fontSize = 25.sp),
                value = noteTitle,
                onValueChange = {
                    noteTitle = it
                },
                placeholder = {
                    Text(text = "Title",
                        color = Color.White,
                        style = TextStyle(fontSize = 25.sp)
                    )
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
                value = noteContent,
                onValueChange = {
                    noteContent = it
                },
                placeholder = {
                    Text(text = "Type Something Here...",
                        fontStyle = FontStyle.Italic,
                        style = TextStyle(fontSize = 20.sp),
                        color = Color.White
                    )
                }
            )
        }
    }
}


@Composable
fun previewTypeNoteScree(){
    TypeNotesScreen(navController = rememberNavController())
}
@Preview(showBackground = true)
@Composable
fun TypeNotesPrev()
{
    previewTypeNoteScree()
}

