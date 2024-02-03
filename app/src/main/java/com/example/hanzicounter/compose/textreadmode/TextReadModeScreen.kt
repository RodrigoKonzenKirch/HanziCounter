package com.example.hanzicounter.compose.textreadmode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hanzicounter.R
import com.example.hanzicounter.viewmodels.TextReadModeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextReadModeScreen(
    onNavigateToTextWriteScreen: () -> Unit,
    modifier: Modifier
) {

    val viewModel = hiltViewModel<TextReadModeViewModel>()
    val text = viewModel.highlightedText.collectAsStateWithLifecycle()
    val charactersAndCounters = viewModel.charsAndCounter.collectAsStateWithLifecycle()

    val showCounter = rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar {
                Row {
                    Button(onClick = onNavigateToTextWriteScreen) {
                        Icon(Icons.Outlined.Edit, "Edit Text" )
                    }
                }
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { showCounter.value = !showCounter.value }
                ) {
                    Text(text = if (showCounter.value) stringResource(R.string.button_hide_hanzi_counter) else stringResource(
                        R.string.button_show_hanzi_counter
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag(stringResource(R.string.read_screen_tag))
        ) {

            TextContent(text.value, Modifier.weight(1f))
            if (showCounter.value){
                TextCounter(
                    charactersAndCounters.value,
                    Modifier.weight(1f),
                    onUpdateCharToHighlight = { charToHighlight ->
                        viewModel.highlightChar(charToHighlight)
                    }
                )
            }

        }

    }
}

@Composable
fun TextContent(text: AnnotatedString, modifier: Modifier) {
    Column(modifier.padding(8.dp)) {
        Text(text = text)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextCounter(
    charsNCounts: List<Pair<Char, Int>>,
    modifier: Modifier,
    onUpdateCharToHighlight: (char: Char) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 80.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.padding(8.dp)

    ){
        items(charsNCounts) {charNCount ->
            Card(
                onClick = { onUpdateCharToHighlight(charNCount.first) },
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Text(text = charNCount.first.toString())
                    Spacer(Modifier.width(8.dp))
                    Text(text = charNCount.second.toString())
                }
            }
        }
    }

}
