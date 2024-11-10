package com.example.circleoffifth.ui


import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import circleoffifth.composeapp.generated.resources.Res
import circleoffifth.composeapp.generated.resources.baseline_emoji_events_24
import circleoffifth.composeapp.generated.resources.baseline_school_24
import circleoffifth.composeapp.generated.resources.baseline_self_improvement_24
import circleoffifth.composeapp.generated.resources.challenge
import circleoffifth.composeapp.generated.resources.challenge_description
import circleoffifth.composeapp.generated.resources.survive
import circleoffifth.composeapp.generated.resources.survive_description
import circleoffifth.composeapp.generated.resources.training
import circleoffifth.composeapp.generated.resources.training_description
import com.example.circleoffifth.ui.theme.LocalColorProvider
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

enum class Destinations(
    val title: StringResource? = null,
    val description: StringResource? = null,
    val image: DrawableResource? = null
) {
    MENU,
    SURVIVE(
        title = Res.string.survive,
        description = Res.string.survive_description,
        image = Res.drawable.baseline_emoji_events_24
    ),
    TRAINING(
        title = Res.string.training,
        description = Res.string.training_description,
        image = Res.drawable.baseline_school_24
    ),
    CHALLENGE(
        title = Res.string.challenge,
        description = Res.string.challenge_description,
        image = Res.drawable.baseline_self_improvement_24
    ),
}

@Composable
fun CircleOfFifthApp(
    isPortraitOrientation: Boolean,
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
        },
        backgroundColor = LocalColorProvider.current.background
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
                MenuScreen(screens = screens, onItemClick = { navController.navigate(it.name) })
            }
            composable(route = Destinations.TRAINING.name) {
                TrainingScreen(isPortraitOrientation = isPortraitOrientation)
            }
            composable(route = Destinations.CHALLENGE.name) {
                ChallengeScreen(isPortraitOrientation = isPortraitOrientation)
            }
            composable(route = Destinations.SURVIVE.name) {
                SurviveScreen(isPortraitOrientation = isPortraitOrientation)
            }
        }
    }
}


@Composable
fun AppBar(
    currentScreen: Destinations,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { currentScreen.title?.let {
            Text(
                text = stringResource(it),
                color = LocalColorProvider.current.primary
            ) }
        },
        backgroundColor = LocalColorProvider.current.primaryContainer,
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = LocalColorProvider.current.primary
                    )
                }
            }
        }
    )
}

