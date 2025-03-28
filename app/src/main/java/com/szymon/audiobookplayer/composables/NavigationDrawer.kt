package com.szymon.audiobookplayer.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.szymon.audiobookplayer.Audiobook
import kotlinx.coroutines.CoroutineScope

@Composable
fun NavigationDrawer(audiobooks: List<Audiobook>, selectedAudiobook: Audiobook, onAudiobookSelected: (Audiobook) -> Unit, drawerState: DrawerState, scope: CoroutineScope) {
    ModalDrawerSheet {
        Spacer(Modifier.height(16.dp))
        Text(
            "Dostępne Audiobooki",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
        AudiobooksScrollableList(audiobooks, selectedAudiobook, onAudiobookSelected, drawerState, scope)
    }
}