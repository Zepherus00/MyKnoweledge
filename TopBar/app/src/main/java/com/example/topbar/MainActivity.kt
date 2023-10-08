package com.example.topbar

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.topbar.ui.theme.TopBarTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var item: ListItem? = null
            TopBarTheme {
                NavHost(
                    navController = navController,
                    startDestination = Constants.MAIN_SCREEN
                ) {
                    composable(Constants.MAIN_SCREEN) {
                        MainScreen(this@MainActivity) { listItem ->
                            item = listItem
                            navController.navigate(Constants.INFO_SCREEN)
                        }
                    }
                    composable(Constants.INFO_SCREEN) {

                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(context: Context, onCLick: (ListItem) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val topBarTitle = remember {
        mutableStateOf("Грибы")
    }
    val mainList = remember {
        mutableStateOf(getListItemsByIndex(0, context))
    }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { host ->
            SnackbarHost(hostState = host) { data ->
                Snackbar(
                    backgroundColor = Color.White,
                    snackbarData = data,
                    shape = RoundedCornerShape(20.dp),
                    contentColor = Color.Black,
                    modifier = Modifier.padding(40.dp)
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = topBarTitle.value)
                },
                backgroundColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                "Snackbar message",
                                actionLabel = "ActionPerformed"
                            )
                            if (result == SnackbarResult.ActionPerformed) {

                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Snackbar"
                        )
                    }
                    IconButton(onClick = {

                    }) {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favourite")
                    }
                }
            )
        },
        drawerContent = {
            DrawerMenu() { event ->
                when (event) {
                    is DrawerEvents.OnItemClick -> {
                        topBarTitle.value = event.title
                        mainList.value = getListItemsByIndex(event.index, context)
                    }
                }
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(mainList.value) { item ->
                MainListItem(item = item) { listItem ->
                    onCLick(listItem)
                }
            }
        }
    }
}

private fun getListItemsByIndex(index: Int, context: Context): List<ListItem> {
    val list = arrayListOf<ListItem>()
    val arrayList = context.resources.getStringArray(IdArrayList.listId[index])
    arrayList.forEach { item ->
        val itemArray = item.split("|")
        list.add(
            ListItem(itemArray[0], itemArray[1], itemArray[2])
        )
    }
    return list
}