package org.example.project.features.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import compose.icons.FeatherIcons
import compose.icons.feathericons.Plus
import org.example.project.navgation.EditOrAddRoute
import org.example.project.repostory.database.NoteDatabase
import org.example.project.repostory.useCase.getDataTime
import org.example.project.theme.MyAppTheme
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScren(navController: NavController) {

    val db = koinInject<NoteDatabase>().NoteDao()

    val lista = db.getAll().collectAsStateWithLifecycle(initialValue = emptyList()).value

    MyAppTheme {
        Scaffold(

            topBar = {

                TopAppBar(
                    title = {
                        Text(
                            text = "Notas"
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    )
                )
            },

            floatingActionButton = {

                FloatingActionButton(
                    onClick = {
                        navController.navigate(EditOrAddRoute(null))
                    },
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ){

                    Icon(
                        imageVector = FeatherIcons.Plus,
                        contentDescription = "add",
                    )
                }

            }
        ) { padding ->

            if (lista.isNotEmpty()){

                LazyColumn(
                    modifier = Modifier
                        .padding(padding)
                ) {

                    items(lista) { nota ->

                        ListItem(
                            headlineContent = {

                                Text(
                                    text = nota.title,
                                    color = MaterialTheme.colorScheme.primary
                                )

                            },
                            overlineContent = {
                                Text(
                                    text = nota.date
                                )
                            },
                            modifier = Modifier
                                .clickable {

                                    navController.navigate(EditOrAddRoute(nota.id))
                                }
                        )
                        HorizontalDivider()
                    }

                }

            } else {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text ="Ainda não há notas!",
                        color = MaterialTheme.colorScheme.primary
                    )
                }

            }

        }
    }
}
