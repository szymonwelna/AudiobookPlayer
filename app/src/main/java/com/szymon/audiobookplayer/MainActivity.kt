package com.szymon.audiobookplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.szymon.audiobookplayer.ui.theme.AudiobookTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudiobookTheme {
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            Spacer(Modifier.height(16.dp))
                            FilledTonalButton(
                                onClick = { /* Open add new audiobook screen */ },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            ) {
                                Text(text = stringResource(R.string.add_new_audiobook))
                            }
                            NavigationDrawerItem(
                                label = { Text(text = "Example element 1") },
                                selected = false,
                                onClick = { scope.launch { drawerState.close() } }
                            )
                            NavigationDrawerItem(
                                label = { Text(text = "Example element 2") },
                                selected = false,
                                onClick = { scope.launch { drawerState.close() } }
                            )
                        }
                    },
                    content = {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
                            Scaffold(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                                topBar = {
                                    AudiobookTopBar(scrollBehavior, drawerState, scope)
                                }
                            ) { innerPadding ->
                                AudiobooksItemList(innerPadding)
                            }
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudiobookTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Audiobook",
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun AudiobooksItemList(innerPadding: PaddingValues) {
    val itemCount = 11
    val items = List(itemCount) { "Przycisk $it" }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        val pairs = items.chunked(2)

        items(pairs.size) { index ->
            val pair = pairs[index]

            if (pair.size == 2) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    AudiobookButton(
                        text = pair[0],
                        modifier = Modifier
                            .weight(1f)
                    )
                    AudiobookButton(
                        text = pair[1],
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            } else {
                Row(modifier = Modifier.fillMaxWidth()) {
                    AudiobookButton(
                        text = pair[0],
                        modifier = Modifier
                            .weight(1f)
                    )
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun AudiobookButton(
    modifier: Modifier = Modifier,
    text: String
) {
    ElevatedButton(
        onClick = { /* Akcja przycisku */ },
        modifier = modifier
            .aspectRatio(1f)
            .padding(12.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Text(text)
    }
}