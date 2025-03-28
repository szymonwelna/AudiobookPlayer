package com.szymon.audiobookplayer.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.szymon.audiobookplayer.Audiobook
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AudiobooksScrollableList(
    audiobooks: List<Audiobook>,
    selectedAudiobook: Audiobook,
    onAudiobookSelected: (Audiobook) -> Unit,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    LazyColumn {
        items(audiobooks) { audiobook ->
            NavigationDrawerItem(
                label = { Text(audiobook.title) },
                selected = audiobook == selectedAudiobook,
                onClick = {
                    onAudiobookSelected(audiobook)
                    scope.launch { drawerState.close() }
                },
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}