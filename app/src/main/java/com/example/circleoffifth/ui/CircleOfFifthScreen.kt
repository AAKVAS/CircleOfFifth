package com.example.circleoffifth.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.circleoffifth.R
import com.example.circleoffifth.ui.viewModel.SurviveViewModel
import com.example.circleoffifth.ui.viewModel.TrialViewModel

enum class Destinations(
    @StringRes val title: Int? = null,
    @DrawableRes val image: Int? = null
) {
    MENU,
    SURVIVE(title = R.string.survive),
    TRAINING(title = R.string.training),
    TRIAL(title = R.string.trial)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CircleOfFifthApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Destinations.valueOf(
        backStackEntry?.destination?.route ?: Destinations.MENU.name
    )

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destinations.MENU.name,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            composable(route = Destinations.MENU.name) {
                val screens: List<Destinations> = listOf(
                    Destinations.TRAINING,
                    Destinations.TRIAL,
                    Destinations.SURVIVE
                )
                MenuScreen(screens, { navController.navigate(it.name) })
            }
            composable(route = Destinations.TRAINING.name) {
                TrainingScreen()
            }
            composable(route = Destinations.TRIAL.name) {
                TrialScreen()
            }
            composable(route = Destinations.SURVIVE.name) {
                SurviveScreen()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: Destinations,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { currentScreen.title?.let {
            Text(text = stringResource(it)) }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Назад"
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun CircleofFiftAppPreview() {
    CircleOfFifthApp()
}