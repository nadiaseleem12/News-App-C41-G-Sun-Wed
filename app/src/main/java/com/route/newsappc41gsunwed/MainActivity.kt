package com.route.newsappc41gsunwed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.route.newsappc41gsunwed.categories.CategoriesScreen
import com.route.newsappc41gsunwed.news.NewsScreen
import com.route.newsappc41gsunwed.news.SearchScreen
import com.route.newsappc41gsunwed.ui.theme.NewsAppC41GSunWedTheme
import com.route.newsappc41gsunwed.utils.topBar.NewsToolbar
import com.route.newsappc41gsunwed.widgets.DrawerContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val settingsViewModel:SettingsViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NewsAppC41GSunWedTheme {
                // Don't Call API HERE
                // Toolbars or Bottom Navigation Views or Navigation Drawers
                // Use Scaffold

                NewsModalNavigationDrawer(viewModel = settingsViewModel)

            }
        }


    }

    @Composable
    fun NewsModalNavigationDrawer(modifier: Modifier = Modifier,viewModel: SettingsViewModel) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        ModalNavigationDrawer(
            drawerState = drawerState, drawerContent = {
                DrawerContent(viewModel = viewModel){
                   navController.popBackStack()
                    if(navController.currentDestination?.route != CategoriesRoute.toString()){
                        navController.navigate(CategoriesRoute)
                    }
                    scope.launch {
                        drawerState.close()
                    }
                }

            }){

            NewsAppNavigation(drawerState,navController)
        }

    }


    @Composable
    private fun NewsAppNavigation(drawerState: DrawerState,navController:NavHostController) {
        NavHost(
            navController = navController,
            startDestination = CategoriesRoute,
        ) {
            // Login Screen - Register Screen- Forgot Password
            composable<CategoriesRoute> {
                CategoriesScreen(drawerState = drawerState,
                    onCategoryClick = { endpointId: String ->
                        navController.navigate(NewsRoute(endpointId))
                    }) {
                    navController.navigate(SearchRoute)
                }
            }
            composable<NewsRoute> {
                val endpointId = it.toRoute<NewsRoute>().endpointId
                NewsScreen(endpointId = endpointId, drawerState = drawerState) {
                    navController.navigate(SearchRoute)
                }
            }
            composable<SearchRoute> {
                SearchScreen()
            }
        }
    }
}




