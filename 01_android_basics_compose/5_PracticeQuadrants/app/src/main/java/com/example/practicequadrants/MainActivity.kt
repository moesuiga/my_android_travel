package com.example.practicequadrants

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.practicequadrants.ui.theme.PracticeQuadrantsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeQuadrantsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Quadrants()
                }
            }
        }
    }
}

@Composable
fun Quadrants(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Row(
            modifier = Modifier.weight(1.0f, false)
        ) {
            OneQuadrant(
                title = stringResource(R.string.text_title_text),
                content = stringResource(R.string.text_content_text),
                color = Color.Green,
                modifier = Modifier.weight(0.5f, false)
            )
            OneQuadrant(
                title = stringResource(R.string.image_title_text),
                content = stringResource(R.string.image_content_text),
                color = Color.Yellow,
                modifier = Modifier.weight(0.5f, false)
            )
        }
        Row(
            modifier = Modifier.weight(1.0f, false)
        ) {
           OneQuadrant(
               title = stringResource(R.string.row_title_text),
               content = stringResource(R.string.row_content_text),
               color = Color.Cyan,
               modifier = Modifier.weight(0.5f, false)
           )
           OneQuadrant(
               title = stringResource(R.string.column_title_text),
               content = stringResource(R.string.column_content_text),
               color = Color.LightGray,
               modifier = Modifier.weight(0.5f, false)
           )
        }
    }
}

@Composable
fun OneQuadrant(title: String, content: String, color: Color, modifier: Modifier = Modifier) {
    Surface(color = color, modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(text = content, textAlign = TextAlign.Justify)
        }
    }
}