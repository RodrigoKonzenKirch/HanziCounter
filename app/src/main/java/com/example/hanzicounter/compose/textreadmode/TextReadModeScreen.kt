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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hanzicounter.viewmodels.TextReadModeViewModel

data class charAndCounter(
    var char: String,
    var counter: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextReadModeScreen() {
    // Dummy data
    val charactersAndCounters = listOf(
        charAndCounter("A", 1),
        charAndCounter("B", 2),
        charAndCounter("C", 3),
        charAndCounter("D", 4),
        charAndCounter("E", 5),
        charAndCounter("F", 6),
        charAndCounter("G", 7),
        charAndCounter("H", 8),
        charAndCounter("I", 9),
        charAndCounter("J", 10),
        charAndCounter("K", 11),
        charAndCounter("L", 12),
        charAndCounter("M", 13),
        charAndCounter("N", 14),
        charAndCounter("O", 15),
        charAndCounter("P", 16),
        charAndCounter("Q", 17),
    )
    val viewModel = hiltViewModel<TextReadModeViewModel>()
    val state = viewModel.highlightedText.collectAsStateWithLifecycle()

    val showCounter = rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar {
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { showCounter.value = !showCounter.value
                        viewModel.updateText("我会说中文和英语。你也会说中文吗")}
                ) {
                    Text(text = if (showCounter.value) "Hide Hanzi Counter" else "Show Hanzi Counter")
                }
            }
        }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            TextContent(state.value, Modifier.weight(1f))
            if (showCounter.value){
                TextCounter(charactersAndCounters, Modifier.weight(1f))
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

@Composable
fun TextCounter(charsNCounts: List<charAndCounter>, modifier: Modifier) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 80.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.padding(8.dp)

    ){
        items(charsNCounts) {charNCount ->
            Card(elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Text(text = charNCount.char)
                    Spacer(Modifier.width(8.dp))
                    Text(text = charNCount.counter.toString())
                }
            }
        }
    }

}
