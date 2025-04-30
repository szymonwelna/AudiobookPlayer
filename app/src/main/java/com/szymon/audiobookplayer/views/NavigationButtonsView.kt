package com.szymon.audiobookplayer.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Composable function implementing buttons that allow playback navigation
 */
@Composable
fun NavigationButtons(
    rewindSeconds: Int = 10,
    forwardSeconds: Int = 10,
    paddingValue: Int = 32,
    sideButtonsSize: Int = 48,
    centralButtonSize: Int = 64
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValue.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left side button (rewind x seconds)
        IconButton (
            onClick = { /*TODO: Implement rewind by $rewindSeconds seconds*/ },
            modifier = Modifier.size(sideButtonsSize.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp, /*TODO add rewind icon*/
                contentDescription = "Rewind" + rewindSeconds.toString() + "seconds",
                modifier = Modifier.size(sideButtonsSize.dp)
            )
        }
        // Central button (play/pause)
        IconButton (
            onClick = { /*TODO: Implement play/pause*/ },
            modifier = Modifier.size(centralButtonSize.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,    /*TODO: add play/pause icon that is affected by current playback state*/
                contentDescription = "Play/Pause", /*TODO: add play/pause content description*/
                modifier = Modifier.size(centralButtonSize.dp)
            )
        }
        // Right side button (forward x seconds)
        IconButton (
            onClick = { /*TODO: Implement forward by $forwardSeconds seconds*/ },
            modifier = Modifier.size(sideButtonsSize.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp, /*TODO add forward icon*/
                contentDescription = "Forward" + forwardSeconds.toString() + "seconds",
                modifier = Modifier.size(sideButtonsSize.dp)
            )
        }
    }
}


@Composable
@Preview
fun NavigationButtonsPreview() {
    NavigationButtons()
}