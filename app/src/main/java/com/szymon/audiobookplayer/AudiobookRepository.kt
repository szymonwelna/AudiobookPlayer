package com.szymon.audiobookplayer

data class Audiobook(
    val title: String,
    val imageRes: Int,
    val audioFileName: String
)

val audiobooks = listOf(
    Audiobook("Granica Możliwości", R.drawable.granica_mozliwosci_okladka, "1_Granica_Mozliwosci.mp3"),
    Audiobook("Okruch Lodu", R.drawable.okruch_lodu_okladka, "1_Granica_Mozliwosci.mp3"),
    Audiobook("Wieczny Ogień", R.drawable.wieczny_ogien_okladka, "1_Granica_Mozliwosci.mp3"),
    Audiobook("Trochę Poświęcenia", R.drawable.troche_poswiecenia_okladka, "1_Granica_Mozliwosci.mp3"),
    Audiobook("Miecz Przeznaczenia", R.drawable.miecz_przeznaczenia_okladka, "1_Granica_Mozliwosci.mp3"),
    Audiobook("Coś Więcej", R.drawable.cos_wiecej_okladka, "1_Granica_Mozliwosci.mp3"),
    Audiobook("Wiedźmin", R.drawable.wiedzmin_okladka, "7_Wiedzmin.mp3"),
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