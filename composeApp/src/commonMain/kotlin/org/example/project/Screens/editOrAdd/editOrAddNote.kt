package org.example.project.Screens.editOrAdd

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.Delete
import compose.icons.feathericons.Save
import kotlinx.coroutines.launch
import org.example.project.components.Dialog
import org.example.project.repostory.database.GetDatabaseBuilder
import org.example.project.repostory.database.Note
import org.example.project.repostory.database.getRoomDatabase
import org.example.project.repostory.useCase.getDataTime
import org.example.project.theme.MyAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditOrAddScreen(
    navController: NavController,
    id: Int?
){

    val db = getRoomDatabase(GetDatabaseBuilder.getDB()!!).NoteDao()

    val viewModel = viewModel{ editOrAddViewModel() }

    val titleApp by viewModel.titleScreen.collectAsStateWithLifecycle()

    val title by viewModel.title.collectAsStateWithLifecycle()

    val text by viewModel.text.collectAsStateWithLifecycle()

    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    val showDialog by viewModel.showDialog.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()


    if (id == null) {

        viewModel.changeTitleScreen("Criar Nota")

    } else {

        scope.launch {

            viewModel.setIsLoadingTrue()

            val actualNote = db.getNoteById(id)

            viewModel.changeTitleScreen("Editando Nota")
            viewModel.changeTitle(actualNote.title)
            viewModel.changeText(actualNote.text)

            viewModel.setIsLoadingFalse()

        }

    }

    MyAppTheme {

        Scaffold(

            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .padding(bottom = 45.dp),

            topBar = {

                TopAppBar(

                    title = { Text(titleApp) },

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        titleContentColor = MaterialTheme.colorScheme.onBackground
                    ),

                    navigationIcon = {

                        IconButton(onClick = {

                            navController.navigateUp()

                        }){

                            Icon(
                                imageVector = FeatherIcons.ArrowLeft,
                                contentDescription = "back",
                                tint = MaterialTheme.colorScheme.onBackground
                            )

                        }

                    },
                    actions = {
                        IconButton(
                            onClick = {
                                scope.launch {

                                    if (id == null){
                                        db.addNote(Note(title = title, text = text, date = getDataTime()))
                                    } else {

                                        db.editNote(Note(id = id ,title = title, text = text, date = getDataTime()))

                                    }

                                }
                            }
                        ){
                            Icon(
                                imageVector = FeatherIcons.Save,
                                contentDescription = "save"
                            )
                        }

                        if (id != null) {

                            IconButton(onClick = {

                                viewModel.setShowDialogTrue()

                            }){

                                Icon(
                                    imageVector = FeatherIcons.Delete,
                                    contentDescription = "delete",
                                    tint = MaterialTheme.colorScheme.onBackground
                                )

                            }
                        }

                    }
                )

            },

            bottomBar = {

                if (id == null){
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onBackground
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),

                        onClick = {

                            scope.launch {

                                if (id == null){
                                    db.addNote(Note(title = title, text = text, date = getDataTime()))
                                } else {

                                    db.editNote(Note(id = id ,title = title, text = text, date = getDataTime()))

                                }

                            }

                            navController.navigateUp()

                        }) {
                        Text("Salvar")
                    }
                }

            }

        ) { paddingValues ->

           if (isLoading){

               Column(
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally,
                   modifier = Modifier.fillMaxSize()
               ) {
                   CircularProgressIndicator()
               }

           } else {

               Column(
                   modifier = Modifier
                       .fillMaxSize()
                       .padding(paddingValues)
                       .padding(10.dp)
               ) {

                   TextField(
                       value = title,
                       onValueChange = {viewModel.changeTitle(it)},
                       placeholder = {

                           Text(
                               text = "Titulo",
                               fontWeight = FontWeight.Bold
                           )

                       },
                       modifier = Modifier.fillMaxWidth(),
                       colors = TextFieldDefaults.colors(
                           focusedContainerColor = MaterialTheme.colorScheme.background,
                           unfocusedContainerColor = MaterialTheme.colorScheme.background,
                           focusedIndicatorColor = MaterialTheme.colorScheme.background,
                           unfocusedIndicatorColor = MaterialTheme.colorScheme.background
                       )
                   )

                   Spacer(Modifier.height(13.dp))

                   HorizontalDivider()

                   Spacer(Modifier.height(13.dp))

                   TextField(
                       value = text,
                       onValueChange = {viewModel.changeText(it)},
                       placeholder = { Text("Texto") },
                       modifier = Modifier.fillMaxSize(),
                       colors = TextFieldDefaults.colors(
                           focusedContainerColor = MaterialTheme.colorScheme.background,
                           unfocusedContainerColor = MaterialTheme.colorScheme.background,
                           focusedIndicatorColor = MaterialTheme.colorScheme.background,
                           unfocusedIndicatorColor = MaterialTheme.colorScheme.background
                       )
                   )

               }

           }

        }

        if (showDialog){

            Dialog(
                title = "Atenção!",
                text = "Deseja mesmo apagar esta nota?",
                onComfirm = {
                    scope.launch{ db.deleteNote(db.getNoteById(id!!)) }
                    viewModel.setShowDialogFalse()
                    navController.navigateUp()
                },
                onDismissRequest = {viewModel.setShowDialogFalse()}
            )

        }

    }

}