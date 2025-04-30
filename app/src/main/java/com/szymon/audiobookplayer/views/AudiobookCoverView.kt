package com.szymon.audiobookplayer.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import com.szymon.audiobookplayer.R


/**
 * Composable function displaying only the image of audiobook cover with modifiable rounding and elevation
 */
@Composable
fun AudiobookCover(
    modifier: Modifier = Modifier,
    image: Int = R.drawable.default_cover,
    imageDescription: String? = "Audiobook Cover",
    imageRounding: Int = 16,    // Image rounding in dp (passed as Int)
    shadowElevation: Int = 8    // Shadow elevation in dp (passed as Int)
) {
    Image(
        painter = painterResource(image),
        contentDescription = imageDescription,
        modifier = modifier
            .aspectRatio(1f) // Image is set to have 1:1 proportions (square)
            .clip(RoundedCornerShape(imageRounding.dp))
            .shadow(shadowElevation.dp, RoundedCornerShape(imageRounding.dp))
    )
}

@Composable
@Preview
fun AudiobookCoverPreview() {
    AudiobookCover()
}