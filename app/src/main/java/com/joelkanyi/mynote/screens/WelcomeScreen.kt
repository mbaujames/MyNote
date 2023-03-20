package com.joelkanyi.mynote.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joelkanyi.mynote.data.NoteDatabase
import com.joelkanyi.mynote.navigation.Screen

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddNotesScreen (
    title: String = "",
    content: String = "",
    id: Int? = null,

    navController: NavController,
){
    val context = LocalContext.current
    val database = NoteDatabase.getDatabase(context)
    val noteDao = database.noteDao()
    val notes = noteDao.getAllNotes().collectAsState(initial = emptyList()).value


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
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                modifier = Modifier.padding(12.dp),
                                text = "My Notes",
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                modifier = Modifier
                                    .padding(end = 40.dp)
                                    .size(25.dp),
                                contentDescription = null,
                                tint = Color.White
                            )
                            Icon(
                                imageVector = Icons.Default.Info,
                                modifier = Modifier
                                    .padding(end = 20.dp)
                                    .size(25.dp),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color.Gray,
                onClick = {
                    navController.navigate(Screen.AddNote.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = com.joelkanyi.mynote.R.drawable.pic),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                color = Color.White,
                text = "Create your First Note !",
                style = TextStyle(fontSize = 20.sp)
            )
        }
    }


}
@Composable
fun PreviewNotesScreen() {
    AddNotesScreen(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    PreviewNotesScreen()
}