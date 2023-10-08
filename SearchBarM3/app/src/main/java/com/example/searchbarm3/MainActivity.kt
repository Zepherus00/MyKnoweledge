package com.example.searchbarm3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.searchbarm3.ui.theme.SearchBarM3Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val searchText = remember {
                mutableStateOf("")
            }
            val mainList = remember {
                mutableStateOf(Utils.originUsersList)
            }
            val isActive = remember {
                mutableStateOf(false)
            }

            SearchBarM3Theme {
                Column(Modifier.fillMaxSize()) {
                    SearchBar(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        query = searchText.value,
                        onQueryChange = { text ->
                            searchText.value = text
                            mainList.value = Utils.search(text)
                        },
                        onSearch = { text ->
                            isActive.value = false
                        },
                        /*colors = SearchBarDefaults.colors(
                            containerColor = Color.Black,
                            inputFieldColors = TextFieldDefaults.colors(focusedTextColor = Color.White)
                        ),*/
                        placeholder = {
                            Text(text = "Search...", color = Color.White)
                        },
                        active = isActive.value,
                        onActiveChange = {
                            isActive.value = it
                        }
                    ) {
                        LazyColumn {
                            items(mainList.value) { item ->
                                Box(
                                    modifier = Modifier
                                        .clickable {
                                            mainList.value = Utils.search(item)
                                        }
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = item)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

object Utils {
    val originUsersList = listOf(
        "Andrey",
        "Sergey",
        "Egor",
        "Semen",
        "Masha",
        "Yula"
    )

    fun search(text: String): List<String> {
        return originUsersList.filter {
            it.lowercase().startsWith(text.lowercase())
        }
    }
}