package br.com.fiap.trashit.view.navbar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun BottomNavigation(navController: NavController) {
    var selectedItem by remember { mutableIntStateOf(1) }
    val items = listOf(
        BottomNavItem.Coletas,
        BottomNavItem.Lixeira,
        BottomNavItem.Conta
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                label = { Text(text = item.title)},
                selected = selectedItem == index,
                onClick = { selectedItem = index
                            navController.navigate(item.screenRoute)
                          },
                icon = { Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.title
                )}
            )
        }
    }
}