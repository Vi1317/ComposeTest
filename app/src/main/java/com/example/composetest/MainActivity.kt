package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val testContact = Contact(
                name = "Евгений",
                surname = "Андреевич",
                familyName = "Лукашин",
                isFavorite = true,
                phone = "+7 495 495 95 95",
                address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                email = "luka@ya.ru"
            )
            ContactDetails(testContact)
        }
    }
}

@Composable
fun ContactDetails(contact: Contact) {
    Column (
        modifier = Modifier.padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ContactPhoto(contact)

        Text(
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            text = "${contact.name} ${contact.surname.orEmpty()}")

        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                fontSize = 24.sp,
                text = contact.familyName
            )
            if (contact.isFavorite) {
                Image(
                    modifier = Modifier.padding(start = 4.dp),
                    painter = painterResource(id = android.R.drawable.star_big_on),
                    contentDescription = null
                )
            }
        }

        InfoRow(contact)
    }
}

@Composable
fun ContactPhoto(contact: Contact) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        if (contact.imageRes == null) {
            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(id = R.drawable.circle),
                contentDescription = null
            )
            Text(
                fontSize = 24.sp,
                text = "${contact.name.take(1)}${contact.familyName.take(1)}"
            )
        } else {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = contact.imageRes),
                contentDescription = null
            )
        }
    }
}

@Composable
fun InfoRow(contact: Contact) {
    Row (
        modifier = Modifier.padding(top = 30.dp),
        verticalAlignment = Alignment.CenterVertically
        ) {
        Text(
            modifier = Modifier
                .weight(0.5F)
                .padding(end = 2.dp),
            textAlign = TextAlign.End,
            fontStyle = FontStyle.Italic,
            fontSize = 18.sp,
            text = "${stringResource(R.string.phone)}: ")
        Text(
            modifier = Modifier.weight(0.5F),
            fontSize = 14.sp,
            text = contact.phone)
    }
    Row (
        modifier = Modifier.padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
        ) {
        Text(
            modifier = Modifier
                .weight(0.5F)
                .padding(end = 2.dp),
            textAlign = TextAlign.End,
            fontStyle = FontStyle.Italic,
            fontSize = 18.sp,
            text = "${stringResource(R.string.address)}: ")
        Text(
            modifier = Modifier.weight(0.5F),
            fontSize = 14.sp,
            text = contact.address)
    }
    if (contact.email != null) {
        Row (
            modifier = Modifier.padding(top = 30.dp),
            verticalAlignment = Alignment.CenterVertically
            ) {
            Text(
                modifier = Modifier
                    .weight(0.5F)
                    .padding(end = 2.dp),
                textAlign = TextAlign.End,
                fontStyle = FontStyle.Italic,
                fontSize = 18.sp,
                text = "${stringResource(R.string.email)}: ")
            Text(
                modifier = Modifier.weight(0.5F),
                fontSize = 14.sp,
                text = contact.email)
        }
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactDetailsPreview() {
    val contact = Contact(
        name = "Евгений",
        surname = "Андреевич",
        familyName = "Лукашин",
        isFavorite = true,
        phone = "+7 495 495 95 95",
        address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
        email = "luka@ya.ru"
    )

    ContactDetails(contact)
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactDetailsPreviewWithPhoto() {
    val contact = Contact(
        name = "Василий",
        familyName = "Кузякин",
        imageRes = R.drawable.example,
        phone = "---",
        address = "Ивановская область, дер. Крутово, д. 4"
    )

    ContactDetails(contact)
}