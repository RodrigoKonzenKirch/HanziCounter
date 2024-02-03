package com.example.hanzicounter.compose.textwritemode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hanzicounter.R
import com.example.hanzicounter.viewmodels.TextWriteModeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextWriteModeScreen(
    onNavigateToTextReadScreen: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier,
) {

    var value by rememberSaveable { mutableStateOf("") }
    val viewModel = hiltViewModel<TextWriteModeViewModel>()

    Scaffold(
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround) {
                    Button(
                        onClick = { viewModel.saveText(value) }
                    ) {
                        Text(text = "Save")
                    }
                    Button(
                        onClick = { value = viewModel.getCurrentText() }
                    ) {
                        Text(text = "Load current text")
                    }
                    Button(
                        onClick = { value = "" }
                    ) {
                        Text(text = "Clear")
                    }
                    Button(onClick = onNavigateBack ) {
                        Text(text = "Cancel")
                    }

                }

            }
        }
    ) { innerPadding ->
        Column(
            modifier
                .padding(innerPadding)
                .testTag(stringResource(R.string.write_screen_tag))
        ) {
            TextField(
                value = value,
                onValueChange = { value = it },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}