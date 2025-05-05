package com.example.budgetfinance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FolderOpen
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.budgetfinance.data.BottomNavigation
import com.example.budgetfinance.data.NavScreen

val items = listOf(
    BottomNavigation(
        title = "Home",
        icon = Icons.Rounded.Home,
        route = NavScreen.navHome
    ),
    BottomNavigation(
        title = "Transactions",
        icon = Icons.Rounded.FolderOpen,
        route = NavScreen.navFinance
    )
)

@Composable
fun BottomNavigationBar(navController: NavController) {
    var curNav by remember { mutableIntStateOf(0) }
    NavigationBar {
        Row(modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)){
            items.forEachIndexed{ index, item ->
                NavigationBarItem(
                    selected = curNav == index,
                    onClick = { curNav = index
                              navController.navigate(item.route)},
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = MaterialTheme.colorScheme.onBackground
                        ) },
                    label = {
                        Text(text = item.title, color = MaterialTheme.colorScheme.onBackground)
                    }
                )
            }
        }
    }
}