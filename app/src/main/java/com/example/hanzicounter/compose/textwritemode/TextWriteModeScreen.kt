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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hanzicounter.R
import com.example.hanzicounter.viewmodels.TextWriteModeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextWriteModeScreen(
    onNavigateToTextReadScreen: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier,
) {

    var value by rememberSaveable { mutableStateOf("") }
    val viewModel = hiltViewModel<TextWriteModeViewModel>()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage = stringResource(R.string.snackbar_text_saved)
    Scaffold(
        topBar = {
                 TopAppBar(
                     title = {
                        Text(text = "Edit or Add new text")
                     }
                 )
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround) {
                    Button(
                        onClick = {
                            viewModel.saveText(value)
                            scope.launch {
                                snackbarHostState.showSnackbar(snackbarMessage)
                            }
                        }
                    ) {
                        Text(text = stringResource(R.string.button_save))
                    }
                    OutlinedButton(
                        onClick = { value = viewModel.getCurrentText() }
                    ) {
                        Text(text = stringResource(R.string.button_load_current_text))
                    }
                    OutlinedButton(
                        onClick = { value = "" }
                    ) {
                        Text(text = stringResource(R.string.button_clear))
                    }
                    Button(onClick = onNavigateBack ) {
                        Text(text = stringResource(R.string.button_cancel))
                    }

                }

            }
        },
        snackbarHost = {
            SnackbarHost( hostState = snackbarHostState)
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
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("write_mode_edit_text")
            )
        }
    }
}