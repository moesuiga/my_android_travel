package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyArtSpace()
                }
            }
        }
    }
}

@Composable
fun MyArtSpace(modifier: Modifier = Modifier) {
    val firstImage = R.drawable.pixiv_107220075
    val firstTitle = "Asuna"
    val firstArtist = "Fruitstealer"
    val firstYear = 2023
    val secondImage = R.drawable.pixiv_105592451
    val secondTitle = "鶴の嫁入り"
    val secondArtist = "torino"
    val secondYear = 2023
    val thirdImage = R.drawable.pixiv_71542632
    val thirdTitle = "ジャンヌ三姉妹"
    val thirdArtist = "いわさき＠FANBOX"
    val thirdYear = 2018

    var current by remember {
        mutableStateOf(1)
    }

    var currentImage by remember {
        mutableStateOf(firstImage)
    }
    var currentTitle by remember {
        mutableStateOf(firstTitle)
    }
    var currentArtist by remember {
        mutableStateOf(firstArtist)
    }
    var currentYear by remember {
        mutableStateOf(firstYear)
    }

    fun setCurrent(n: Int) {
        current = n
        when (n) {
            1 -> {
                currentImage = firstImage
                currentTitle = firstTitle
                currentArtist = firstArtist
                currentYear = firstYear
            }
            2 -> {
                currentImage = secondImage
                currentTitle = secondTitle
                currentArtist = secondArtist
                currentYear = secondYear
            }
            3 -> {
                currentImage = thirdImage
                currentTitle = thirdTitle
                currentArtist = thirdArtist
                currentYear = thirdYear
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 32.dp, end = 32.dp, top = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ArtImageSurface(
            image = currentImage,
            modifier = Modifier.weight(2.0f)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            ArtInfo(currentTitle, currentArtist, currentYear, modifier = Modifier)
            Spacer(modifier = Modifier.height(8.dp))
            ArtFooter(
                onPrevious = {
                    when (current) {
                        1 -> setCurrent(3)
                        2 -> setCurrent(1)
                        3 -> setCurrent(2)
                    }
                },
                onNext = {
                    when (current) {
                        1 -> setCurrent(2)
                        2 -> setCurrent(3)
                        3 -> setCurrent(1)
                    }
                }
            )
        }
    }
}

@Composable
fun ArtImageSurface(@DrawableRes image: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .wrapContentSize(align = Alignment.Center)
            .border(width = 2.dp, color = Color.Gray)
            .shadow(elevation = 12.dp)
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier
                .padding(12.dp),
        )
    }
}

@Composable
fun ArtInfo(title: String, artist: String, year: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .wrapContentSize()
            .shadow(elevation = 6.dp)
    ) {
        Column(
            modifier = modifier
                .wrapContentSize()
                .padding(top = 4.dp, bottom = 4.dp, start = 12.dp, end = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = title, fontSize = 16.sp)
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(color = Color.Magenta, fontWeight = FontWeight.Bold)
                    ) {
                        append(artist)
                    }
                    append(" ")
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append("($year)")
                    }
                },
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun ArtFooter(onPrevious: () -> Unit, onNext: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPrevious,
            modifier = Modifier
                .wrapContentSize(align = Alignment.Center)
                .width(120.dp)
        ) {
            Text(text = stringResource(R.string.previous))
        }
        Button(
            onClick = onNext,
            modifier = Modifier
                .wrapContentSize(align = Alignment.Center)
                .width(120.dp)
        ) {
            Text(text = stringResource(R.string.next))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        MyArtSpace()
    }
}