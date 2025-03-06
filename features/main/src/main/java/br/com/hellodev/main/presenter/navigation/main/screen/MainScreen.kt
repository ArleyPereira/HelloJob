package br.com.hellodev.main.presenter.navigation.main.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.hellodev.design.presenter.components.bottom.bar.BottomBarItemUI
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.main.presenter.navigation.host.BottomAppBarNavHost
import br.com.hellodev.main.presenter.navigation.main.items.BottomAppBarItems
import br.com.hellodev.main.presenter.navigation.routes.BottomBarRoutes

@Composable
fun MainScreen(
    //onBackPressed: () -> Unit
) {
    //val viewModel = koinViewModel<MainViewModel>()
    //val state by viewModel.state.collectAsStateWithLifecycle()

    MainContent(
        //state = state,
        //action = viewModel::dispatchAction,
        //onBackPressed = onBackPressed
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainContent(
    //state: MainState,
    //action: (MainAction) -> Unit,
    //onBackPressed: () -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = isBottomAppBar(currentDestination),
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
                content = {
                    BottomAppBar(
                        actions = {
                            BottomAppBarItems.items.forEach { item ->
                                val isSelect = currentDestination?.hierarchy?.any {
                                    it.route == item.route::class.qualifiedName
                                } == true

                                BottomBarItemUI(
                                    selectedIcon = painterResource(item.selectedIcon),
                                    unselectedIcon = painterResource(item.unselectedIcon),
                                    label = stringResource(item.label),
                                    isSelect = isSelect,
                                    onClick = {
                                        navController.navigate(item.route) {
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                        containerColor = HelloTheme.colorScheme.screen.backgroundPrimary
                    )
                }
            )
        },
        content = {
            BottomAppBarNavHost(
                modifier = Modifier
                    .background(HelloTheme.colorScheme.screen.backgroundPrimary),
                navHostController = navController
            )
        }
    )
}

fun isBottomAppBar(currentDestination: NavDestination?): Boolean {
    return when (currentDestination?.route) {
        BottomBarRoutes.Home::class.qualifiedName,
        BottomBarRoutes.Saved::class.qualifiedName,
        BottomBarRoutes.ApplicationList::class.qualifiedName,
        BottomBarRoutes.Message::class.qualifiedName,
        BottomBarRoutes.Account::class.qualifiedName -> true

        else -> false
    }
}

@PreviewLightDark
@Composable
private fun MainPreview() {
    HelloTheme {
        MainContent(
            //state = MainState(),
            //action = {},
            //onBackPressed = {}
        )
    }
}