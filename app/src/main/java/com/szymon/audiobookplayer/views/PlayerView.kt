package com.szymon.audiobookplayer.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Composable function that displays current audiobook and allows navigation with components such as:
 * Cover -> AudiobookCover from AudiobookCoverView class
 * Title -> AudiobookTitle from AudiobookTitleView class
 * Navigation buttons -> NavigationButtons from NavigationButtonsView class
 */

@Composable
fun Player(modifier: Modifier = Modifier) {
    /*TODO: Implement player view, adjust layout of components and add functionality */
    Column(
         horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AudiobookCover(
            modifier = Modifier
                .fillMaxWidth(0.7f)
        )
        AudiobookTitle()
        NavigationButtons()
    }
}

@Composable
@Preview
fun PlayerPreview() {
    Player()
}