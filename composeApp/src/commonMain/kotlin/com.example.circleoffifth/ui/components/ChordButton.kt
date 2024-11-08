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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import circleoffifth.composeapp.generated.resources.Res
import circleoffifth.composeapp.generated.resources.baseline_music_note_24
import circleoffifth.composeapp.generated.resources.play_chord
import com.example.circleoffifth.ui.theme.CommonTheme
import com.example.circleoffifth.ui.theme.btnHeight
import com.example.circleoffifth.ui.theme.btnWidth
import com.example.circleoffifth.ui.theme.cardCornerRadius
import com.example.circleoffifth.ui.theme.paddingSmall
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ChordButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .height(btnHeight.margin)
            .width(btnWidth.margin)
            .clip(RoundedCornerShape(cardCornerRadius.margin))
            .clickable { onClick() }
            .background(CommonTheme.primaryContainer),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painterResource(Res.drawable.baseline_music_note_24),
            contentDescription = stringResource(Res.string.play_chord),
            alignment = Alignment.Center,
            contentScale =  ContentScale.Crop,
            colorFilter = ColorFilter.tint(color = CommonTheme.primary),
            modifier = modifier
                .size(btnHeight.margin)
                .padding(paddingSmall.margin)
        )
    }
}
