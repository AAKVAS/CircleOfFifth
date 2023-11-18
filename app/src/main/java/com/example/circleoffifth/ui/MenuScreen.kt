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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.circleoffifth.R
import com.example.circleoffifth.ui.theme.CircleOfFifthTheme

@Composable
fun MenuScreen(
    screens: List<Destinations>,
    modifier: Modifier = Modifier,
    onItemClick: (_: Destinations) -> Unit = {},
) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuItem(
    screen: Destinations,
    onItemClick: (_: Destinations) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(screen) },
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
        ) {
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small))
            ) {
                Image(
                    painter = painterResource(screen.image!!),
                    contentDescription = stringResource(screen.title!!),
                    alignment = Alignment.Center,
                    contentScale =  ContentScale.Crop,
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .padding(end = dimensionResource(id = R.dimen.padding_small))
                        .size(dimensionResource(R.dimen.icon_medium))
                )
                Text(
                    text = stringResource(id = screen.title),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            Text(
                text = stringResource(id = screen.description!!),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.padding_small),
                    bottom = dimensionResource(id = R.dimen.padding_medium)
                )
            )
        }
    }
}

@Preview
@Composable
fun MenuItemPreview() {
    CircleOfFifthTheme {
        Surface {
            MenuItem(
                screen = Destinations.SURVIVE,
                onItemClick = {}
            )
        }
    }
}