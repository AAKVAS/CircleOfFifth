package com.example.circleoffifth.ui.components

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.circleoffifth.data.Chord
import java.lang.Math.pow
import kotlin.math.atan2


@Composable
fun CircleOfFifth(
    modifier: Modifier = Modifier,
    onChordClick: (_: Chord) -> Unit = {}
) {
    val outerChordList: List<Chord> = listOf(
        Chord.C,
        Chord.G,
        Chord.D,
        Chord.A,
        Chord.E,
        Chord.B,
        Chord.F_SHARP,
        Chord.D_FLAT,
        Chord.A_FLAT,
        Chord.E_FLAT,
        Chord.B_FLAT,
        Chord.F
    )
    val innerChordList: List<Chord> = listOf(
        Chord.A_MINOR,
        Chord.E_MINOR,
        Chord.B_MINOR,
        Chord.F_SHARP_MINOR,
        Chord.C_SHARP_MINOR,
        Chord.G_SHARP_MINOR,
        Chord.E_FLAT_MINOR,
        Chord.B_FLAT_MINOR,
        Chord.F_MINOR,
        Chord.C_MINOR,
        Chord.G_MINOR,
        Chord.D_MINOR
    )

    val textMeasurer = rememberTextMeasurer()
    var diameter by remember { mutableFloatStateOf(0f) }
    var center by remember { mutableStateOf(Offset(0f, 0f)) }
    var radius by remember { mutableFloatStateOf(0f) }
    var innerRadius by remember { mutableFloatStateOf(0f) }
    var mediumRadius by remember { mutableFloatStateOf(0f) }
    var outerRadius by remember { mutableFloatStateOf(0f) }
    val outerCircleColor = MaterialTheme.colorScheme.primaryContainer

    Column(
        modifier = modifier
            .aspectRatio(1f)
            .onGloballyPositioned {
                diameter = minOf(it.size.width, it.size.height).toFloat()
                center = Offset(diameter / 2, diameter / 2)
                radius = diameter / 13
                innerRadius = radius * 2.5f
                mediumRadius = radius * 3.5f
                outerRadius = radius * 5.5f
            }
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        findChord(
                            offset = it,
                            center = center,
                            innerRadius = innerRadius,
                            mediumRadius = mediumRadius,
                            outerRadius = outerRadius,
                            outerChordList = outerChordList,
                            innerChordList = innerChordList,
                            halfCircleWidth = radius
                        )?.let { chord ->
                            onChordClick(chord)
                        }
                    })
                }
        ) {
            DrawChordList(this, outerRadius, center, radius * 2f, outerChordList, textMeasurer, outerCircleColor)
            DrawChordList(this, mediumRadius, center, radius * 2f, innerChordList, textMeasurer, Color.White)
            DrawChordBorders(this, innerRadius, center, radius * 4f)
            drawCircle(color = Color.Black, radius = radius * 2.5f, center = center, style = Stroke(width = 2f))
        }
    }
}

fun DrawChordList(
    drawScope: DrawScope,
    radius: Float,
    center: Offset,
    thickness: Float,
    outerChordList: List<Chord>,
    textMeasurer: TextMeasurer,
    circleColor: Color
) {
    drawScope.drawCircle(color = circleColor, radius = radius, center = center, style = Stroke(width = thickness))
    drawScope.drawCircle(color = Color.Black, radius = radius + thickness / 2, center = center, style = Stroke(width = 2f))

    var currentAngle = -90f
    outerChordList.forEach { chord ->
        val radius = radius
        val angleOffset = 360f / outerChordList.size

        val textLayoutResult = textMeasurer.measure(
            text = chord.title,
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = thickness.sp / 8
            )
        )

        val textWidth = textLayoutResult.size.width
        val textHeight = textLayoutResult.size.height

        val textOffset = Offset(
            center.x + radius * Math.cos(Math.toRadians(currentAngle.toDouble())).toFloat() - textWidth / 2,
            center.y + radius * Math.sin(Math.toRadians(currentAngle.toDouble())).toFloat() - textHeight / 2
        )

        drawScope.drawText(
            textLayoutResult,
            topLeft = textOffset,
            color = Color.Black
        )
        currentAngle += angleOffset
    }
}

fun DrawChordBorders(
    drawScope: DrawScope,
    radius: Float,
    center: Offset,
    thickness: Float,
) {
    var currentAngle = -45f
    for (i in 0 until 12) {
        val angleOffset = 360f / 12

        val start = Offset(
            x = center.x + radius * Math.cos(Math.toRadians(currentAngle.toDouble())).toFloat(),
            y = center.y + radius * Math.sin(Math.toRadians(currentAngle.toDouble())).toFloat()
        )

        val end = Offset(
            x = center.x + (radius + thickness) * Math.cos(Math.toRadians(currentAngle.toDouble())).toFloat(),
            y = center.y + (radius + thickness) * Math.sin(Math.toRadians(currentAngle.toDouble())).toFloat()
        )

        drawScope.drawLine(
            color = Color.Black,
            start = start,
            end = end,
            strokeWidth = 2f
        )
        currentAngle += angleOffset
    }
}

fun findChord(
    offset: Offset,
    center: Offset,
    innerRadius: Float,
    mediumRadius: Float,
    outerRadius: Float,
    outerChordList: List<Chord>,
    innerChordList: List<Chord>,
    halfCircleWidth: Float
): Chord? {
    val dx = (center.x - offset.x).toDouble()
    val dy = (center.y - offset.y).toDouble()

    // Внутри пустого внутреннего круга
    if (insideCircle(dx, dy, innerRadius.toDouble())) {
        return null
    }

    // Клик внутри минорного круга
    if (insideCircle(dx, dy, mediumRadius.toDouble() + halfCircleWidth)) {
        return innerChordList[calculateChordIndex(dx, dy)]
    }

    // Клик внутри мажорного круга
    if (insideCircle(dx, dy, outerRadius.toDouble() + halfCircleWidth)) {
        return outerChordList[calculateChordIndex(dx, dy)]
    }
    return null
}

fun insideCircle(dx: Double, dy: Double, radius: Double): Boolean =
    pow(dx, 2.0) + pow(dy, 2.0) <= pow(radius, 2.0)

fun calculateChordIndex(
    dx: Double,
    dy: Double
): Int {
    val angle = (180 / Math.PI * atan2(dy, dx)).toFloat() - 90
    val posAngle = if (angle < 0) angle + 360  else angle
    Log.d("ANGLE", posAngle.toString())
    return ((posAngle + 15) % 360 / (360 / 12)).toInt()
}


@Preview(uiMode = Configuration.ORIENTATION_LANDSCAPE)
@Composable
fun CircleofFiftPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CircleOfFifth(modifier = Modifier
            .fillMaxWidth())
    }
}