package com.example.circleoffifth.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.circleoffifth.R
import com.example.circleoffifth.ui.theme.CircleOfFifthTheme

@Composable
fun ChordButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .height(dimensionResource(R.dimen.btn_height))
            .width(dimensionResource(id = R.dimen.btn_width))
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painterResource(id = R.drawable.baseline_music_note_24),
            contentDescription = stringResource(R.string.play_chord),
            alignment = Alignment.Center,
            contentScale =  ContentScale.Crop,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary),
            modifier = modifier
                .size(dimensionResource(R.dimen.btn_height))
                .padding(dimensionResource(id = R.dimen.padding_small))
        )
    }
}

@Preview
@Composable
fun ChordButtonPreview() {
    CircleOfFifthTheme {
        Surface {
            ChordButton()
        }
    }
}