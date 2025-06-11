package org.example.project.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import compose.icons.FeatherIcons
import compose.icons.feathericons.AlertTriangle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dialog(
    title: String,
    text:String,
    onComfirm: () -> Unit,
    onDismissRequest: () -> Unit
){

    BasicAlertDialog(
        onDismissRequest = { onDismissRequest() }
    ) {

        Card {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(10.dp)
            ) {

                Icon(
                    imageVector = FeatherIcons.AlertTriangle,
                    contentDescription = "Alert"
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = title,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = text
                )

                Spacer(Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Button(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.fillMaxWidth(0.5f)
                    ){
                        Text("Não")
                    }

                    Spacer(Modifier.width(5.dp))

                    Button(
                        onClick = { onComfirm() },
                        modifier = Modifier.fillMaxWidth(1f)
                    ){
                        Text("Sim")
                    }
                }


            }

        }

    }

}