package com.example.circleoffifth.ui

import android.content.res.Configuration
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.circleoffifth.R
import com.example.compose.CircleOfFifthTheme

enum class Destinations(
    @StringRes val title: Int? = null,
    @StringRes val description: Int? = null,
    @DrawableRes val image: Int? = null
) {
    MENU,
    SURVIVE(
        title = R.string.survive,
        description = R.string.survive_description,
        image = R.drawable.baseline_emoji_events_24
    ),
    TRAINING(
        title = R.string.training,
        description = R.string.training_description,
        image = R.drawable.baseline_school_24
    ),
    CHALLENGE(
        title = R.string.challenge,
        description = R.string.challenge_description,
        image = R.drawable.baseline_self_improvement_24
    ),
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
                    Destinations.CHALLENGE,
                    Destinations.SURVIVE
                )
                MenuScreen(screens, { navController.navigate(it.name) })
            }
            composable(route = Destinations.TRAINING.name) {
                TrainingScreen()
            }
            composable(route = Destinations.CHALLENGE.name) {
                ChallengeScreen()
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
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        return
    }
    TopAppBar(
        title = { currentScreen.title?.let {
            Text(
                text = stringResource(it),
                color = MaterialTheme.colorScheme.primary
            ) }
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
                        contentDescription = "Назад",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun CircleofFiftAppPreview() {
    CircleOfFifthTheme {
        Surface {
            CircleOfFifthApp()
        }
    }
}