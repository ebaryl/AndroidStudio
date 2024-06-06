package com.example.newproject

import ContactsListViewModel
import android.graphics.Color
import android.graphics.Color.GREEN
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newproject.ui.theme.NewProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private val viewModel: ContactsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewState = viewModel.viewState
            NewProjectTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        MediumTopAppBar(title = { Text(text = "Kontakty") })
                    }
                ) {
                    ContactsList(
                        viewState = viewState,
                        paddingValues = it,
                        imageUrl = null
                    )
                }
            }
        }
    }
}

@Composable
fun ContactsList(
    viewState: ContactsListViewState,
    paddingValues: PaddingValues,
    imageUrl: Nothing?,
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        item {
            HeaderItem(text = "Kontakty")
        }

        items(viewState.list) {contact ->
            ContactItem(
                profileImageUrl = contact.imageUrl,
                name = contact.name,
                isFavourite = contact.isFavourite
            )
        }
    }
}

@Composable
fun HeaderItem(text: String) {
    Text(text = text)
}

@Composable
fun ContactItem(
    profileImageUrl: String?,
    name: String,
    isFavourite: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Profile Image
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Profile Image",
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
        )

        // Spacer to push the name to the center
        Spacer(modifier = Modifier.width(10.dp))

        // Contact Name
        Text(
            text = name,
            modifier = Modifier.weight(1f) // This will ensure the name takes up all available space between the images
        )

        //if (isFavourite) {
            Icon(imageVector = Icons.Default.Star,
                contentDescription = "")
        //}
    }
}

@Preview(showBackground = true)
@Composable
fun ContactsListPreview() {
    NewProjectTheme {
        ContactsList(
            viewState = ContactsListViewState(
                list = generateFakeContactsList(100)
            ),
            paddingValues = PaddingValues(all = 0.dp,),
            imageUrl = null
        )
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewProjectTheme {
        Greeting(name = "Android")
    }
}

data class Contact(
    val id: Long,
    val name: String,
    val imageUrl: String? = null,
    val isFavourite: Boolean = false
)

data class ContactsListViewState(
    val list: List<Contact> = emptyList(),
    val recent: List<Contact> = emptyList()
)

private fun generateFakeContact(id: Long): Contact = Contact(
    id = id,
    name = "name $id"
)

fun generateFakeContactsList(count: Int) = mutableListOf<Contact>().apply {
    for (i:Int in 1 .. count) {
        add(generateFakeContact(id = i.toLong()))
    }
}