package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

val FilledIcons = Icons.Filled

val bgColor = Color(0xFF018786)
val frontColor = Color(0xFF93C2B7)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(bgColor),
        verticalArrangement = Arrangement.Center,
    ) {
        Profile(
            modifier = Modifier.weight(1f)
        )
        ContactList()
    }
}

@Composable
fun Profile(modifier: Modifier = Modifier) {
    val avatar = painterResource(id = R.drawable.avatar)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = avatar,
            contentDescription = "Avatar",
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = "Moe Suiga",
            fontSize = 52.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
        Text(
            text = "Front-end developer, web, miniapp, hybrid app, and so on",
            color = frontColor,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun ContactList(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(top = 16.dp, bottom = 40.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        ContactItem(icon = FilledIcons.Phone, iconDesc = "Phone", content = "(+86)12345678")
        ContactItem(icon = FilledIcons.Email, iconDesc = "Email", content = "wyw1121@live.com")
        ContactItem(icon = FilledIcons.Share, iconDesc = null, content = "@moesuiga")
    }
}

@Composable
fun ContactItem(icon: ImageVector, iconDesc: String?, content: String, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(3.dp)
            .background(frontColor)
    ) {}
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 32.dp, top = 8.dp, bottom = 8.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDesc,
            tint = frontColor
        )
        Text(
            text = content,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}
