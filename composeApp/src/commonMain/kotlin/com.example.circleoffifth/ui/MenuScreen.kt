package com.example.circleoffifth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import com.example.circleoffifth.ui.theme.LocalColorProvider
import com.example.circleoffifth.ui.theme.cardCornerRadius
import com.example.circleoffifth.ui.theme.iconMedium
import com.example.circleoffifth.ui.theme.paddingMedium
import com.example.circleoffifth.ui.theme.paddingSmall
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MenuScreen(
    screens: List<Destinations>,
    modifier: Modifier = Modifier,
    onItemClick: (_: Destinations) -> Unit = {},
) {
    LazyColumn(
        contentPadding = PaddingValues(paddingMedium.margin),
        verticalArrangement = Arrangement.spacedBy(paddingMedium.margin),
        modifier = modifier
    ) {
        items(screens, key = { screen -> screen.name }) {screen ->
            MenuItem(
                screen = screen,
                onItemClick = onItemClick,
                modifier = Modifier.fillParentMaxWidth()
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuItem(
    screen: Destinations,
    onItemClick: (_: Destinations) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(cardCornerRadius.margin),
        onClick = { onItemClick(screen) },
        modifier = modifier,
        backgroundColor = LocalColorProvider.current.surfaceVariant
    ) {
        Column(
            modifier = Modifier.padding(horizontal = paddingMedium.margin)
        ) {
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.padding(vertical = paddingSmall.margin)
            ) {
                Image(
                    painter = painterResource(screen.image!!),
                    contentDescription = stringResource(screen.title!!),
                    alignment = Alignment.Center,
                    contentScale =  ContentScale.Crop,
                    colorFilter = ColorFilter.tint(color = LocalColorProvider.current.primary),
                    modifier = Modifier
                        .padding(end = paddingSmall.margin)
                        .size(iconMedium.margin)
                )
                Text(
                    text = stringResource(screen.title),
                    style = MaterialTheme.typography.subtitle1,
                    color = LocalColorProvider.current.primary,
                )
            }
            Text(
                text = stringResource(screen.description!!),
                style = MaterialTheme.typography.body2,
                color = LocalColorProvider.current.primary,
                modifier = Modifier.padding(
                    top = paddingSmall.margin,
                    bottom = paddingMedium.margin
                )
            )
        }
    }
}
