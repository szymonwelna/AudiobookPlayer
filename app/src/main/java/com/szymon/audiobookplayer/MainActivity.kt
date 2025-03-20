package com.szymon.audiobookplayer

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.szymon.audiobookplayer.ui.theme.AudiobookTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudiobookTheme {
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                val audiobooks = listOf(
                    Audiobook("Granica Możliwości", R.drawable.granica_mozliwosci_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Okruch Lodu", R.drawable.okruch_lodu_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Wieczny Ogień", R.drawable.wieczny_ogien_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Trochę Poświęcenia", R.drawable.troche_poswiecenia_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Miecz Przeznaczenia", R.drawable.miecz_przeznaczenia_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Coś Więcej", R.drawable.cos_wiecej_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Wiedźmin", R.drawable.wiedzmin_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Ziarno Prawdy", R.drawable.ziarno_prawdy_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Mniejsze Zło", R.drawable.mniejsze_zlo_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Kwestia Ceny", R.drawable.kraniec_swiata_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Kraniec Świata", R.drawable.kraniec_swiata_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Ostatnie Życzenie", R.drawable.ostatnie_zyczenie_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Krew Elfów", R.drawable.krew_elfow_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Czas Pogardy", R.drawable.czas_pogardy_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Chrzest Ognia", R.drawable.chrzest_ognia_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Wieża Jaskółki", R.drawable.wieza_jaskolki_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Pani Jeziora", R.drawable.pani_jeziora_okladka, "1_Granica_Mozliwosci.mp3"),
                    Audiobook("Sezon Burz", R.drawable.sezon_burz_okladka, "1_Granica_Mozliwosci.mp3")
                )
                var selectedAudiobook by remember { mutableStateOf(audiobooks[0]) }

                AudiobookNavigationDrawer(drawerState, scope, audiobooks, selectedAudiobook) { selectedAudiobook = it }
            }
        }
    }
}

data class Audiobook(
    val title: String,
    val imageRes: Int,
    val audioFileName: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudiobookTopBar(
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Audiobook Wiedźmin",
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch { drawerState.open() }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudiobookNavigationDrawer(
    drawerState: DrawerState,
    scope: CoroutineScope,
    audiobooks: List<Audiobook>,
    selectedAudiobook: Audiobook,
    onAudiobookSelected: (Audiobook) -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(16.dp))
                Text(
                    "Dostępne Audiobooki",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                AudiobooksScrollableList(audiobooks, selectedAudiobook, onAudiobookSelected, drawerState, scope)
            }
        },
        content = {
            Scaffold(
                topBar = {
                    AudiobookTopBar(drawerState, scope)
                }
            ) { innerPadding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AudiobookImage(selectedAudiobook.imageRes, selectedAudiobook.title)
                        BookTitle(selectedAudiobook.title)
                        val context = LocalContext.current
                        NavigationButtons(selectedAudiobook, context)
                    }
                }
            }
        }
    )
}

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


@Composable
fun AudiobookImage(selectedImage: Int, selectedImageDescription: String? = null, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = selectedImage),
        contentDescription = selectedImageDescription,
        modifier = modifier
            .fillMaxWidth(0.7f)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .shadow(8.dp, RoundedCornerShape(16.dp))
    )
}


@Composable
fun BookTitle(selectedAudiobookTitle: String = "Nie wybrano", modifier: Modifier = Modifier) {
    Text(
        text = selectedAudiobookTitle,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
            .padding(16.dp),
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}


@Composable
fun NavigationButtons(selectedAudiobook: Audiobook, context: Context, modifier: Modifier = Modifier) {
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { mediaPlayer?.seekTo((mediaPlayer?.currentPosition ?: 0) - 10000) },
            modifier = Modifier.size(64.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Replay10,
                contentDescription = "Przewiń wstecz 10s",
                modifier = Modifier.size(48.dp)
            )
        }
        IconButton(
            onClick = {
                mediaPlayer?.release()
                mediaPlayer = playAudiobook(context, selectedAudiobook.audioFileName)
                      },
            modifier = Modifier.size(80.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Odtwórz/Pauza",
                modifier = Modifier.size(64.dp)
            )
        }
        IconButton(
            onClick = { mediaPlayer?.seekTo((mediaPlayer?.currentPosition ?: 0) + 10000) },
            modifier = Modifier.size(64.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Forward10,
                contentDescription = "Przewiń do przodu 10s",
                modifier = Modifier.size(48.dp)
            )
        }
    }
}


fun playAudiobook(context: Context, fileName: String): MediaPlayer {
    val mediaPlayer = MediaPlayer()
    try {
        val assetFileDescriptor = context.assets.openFd(fileName)
        mediaPlayer.setDataSource(
            assetFileDescriptor.fileDescriptor,
            assetFileDescriptor.startOffset,
            assetFileDescriptor.length
        )
        assetFileDescriptor.close()
        mediaPlayer.prepare()
        mediaPlayer.start()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return mediaPlayer
}