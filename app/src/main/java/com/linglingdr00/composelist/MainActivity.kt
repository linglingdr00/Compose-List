package com.linglingdr00.composelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.linglingdr00.composelist.data.Contact
import com.linglingdr00.composelist.ui.theme.ComposeListTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

private class ContactComparator : Comparator<Contact> {

    override fun compare(p0: Contact?, p1: Contact?): Int {
        return 1
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val tempList = listOf(
        Contact(0, "Tina"),
        Contact(1, "Carrie")
    )
    ContactList(
        tempList, ContactComparator(), modifier
    )
}

@Composable
fun ContactList(
    contacts: List<Contact>,
    comparator: Comparator<Contact>,
    modifier: Modifier = Modifier
) {
    val sortedContacts = remember(contacts, comparator) {
        contacts.sortedWith(comparator)
    }

    LazyColumn(modifier) {
        items(sortedContacts) {
            ContactListItem("id:${it.id} name:${it.name}")
        }
    }
}

@Composable
fun ContactListItem(contactText: String) {
    Column {
        ListItem(
            headlineContent = { Text(contactText) },
            trailingContent = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                )
            }
        )
        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeListTheme {
        Greeting()
    }
}