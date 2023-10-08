package com.example.topbarm3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer() {
    val items = listOf(
        DrawerItem(
            Icons.Default.Favorite,
            "Favorite"
        ),
        DrawerItem(
            Icons.Default.Add,
            "Add"
        ),
        DrawerItem(
            Icons.Default.AccountBox,
            "Account"
        )
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val selectedItem = remember {
        mutableStateOf(items[0])
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Header",
                    Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(15.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        label = {
                            Text(text = item.title)
                        },
                        selected = selectedItem.value == item,
                        icon = {
                            Icon(imageVector = item.imageVector, contentDescription = item.title)
                        },
                        onClick = {
                            coroutineScope.launch {
                                selectedItem.value = item
                                drawerState.close()
                            }
                        })
                }
            }
        },
        content = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Button(onClick = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }) {
                    Text(text = "Click")
                }
            }
        }
    )
}

data class DrawerItem(
    val imageVector: ImageVector,
    val title: String
)