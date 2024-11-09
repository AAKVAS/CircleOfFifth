package com.example.circleoffifth.utils

import circleoffifth.composeapp.generated.resources.Res
import com.example.circleoffifth.data.Chord
import javazoom.jl.decoder.JavaLayerException
import javazoom.jl.player.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import java.io.ByteArrayInputStream


object JVMChordSoundManager : ChordSoundPlayer {
    private val scope = CoroutineScope(Dispatchers.Default)
    private lateinit var chordSounds: Map<Chord, ByteArray>

    suspend fun create() {
        chordSounds = Chord.entries.associateWith { chord ->
             loadClipForChord(chord)
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    private suspend fun loadClipForChord(chord: Chord): ByteArray {
        val path = when(chord) {
            Chord.A -> "a.mp3"
            Chord.B -> "b.mp3"
            Chord.C -> "c.mp3"
            Chord.D -> "d.mp3"
            Chord.E -> "e.mp3"
            Chord.F -> "f.mp3"
            Chord.G -> "g.mp3"
            Chord.A_FLAT -> "gsharp.mp3"
            Chord.B_FLAT -> "asharp.mp3"
            Chord.D_FLAT -> "csharp.mp3"
            Chord.E_FLAT -> "dsharp.mp3"
            Chord.F_SHARP -> "fsharp.mp3"
            Chord.A_MINOR -> "am.mp3"
            Chord.B_MINOR -> "bm.mp3"
            Chord.C_MINOR -> "cm.mp3"
            Chord.D_MINOR -> "dm.mp3"
            Chord.E_MINOR -> "em.mp3"
            Chord.F_MINOR -> "fm.mp3"
            Chord.G_MINOR -> "gm.mp3"
            Chord.B_FLAT_MINOR -> "amsharp.mp3"
            Chord.C_SHARP_MINOR -> "cmsharp.mp3"
            Chord.E_FLAT_MINOR -> "dmsharp.mp3"
            Chord.F_SHARP_MINOR -> "fmsharp.mp3"
            Chord.G_SHARP_MINOR -> "gmsharp.mp3"
        }
        return Res.readBytes("files/$path")
    }

    override fun playChord(chord: Chord) {
        scope.launch {
            chordSounds[chord]?.let { chordSoundArray ->
                try {
                    val player = Player(ByteArrayInputStream(chordSoundArray))
                    player.play()
                } catch (e: JavaLayerException) {
                    println("Error playing chord ${chord.name}: ${e.message}")
                }
            }
        }
    }

    fun release() {
        scope.cancel()
    }
}

actual fun getChordSoundPlayer(): ChordSoundPlayer {
    return JVMChordSoundManager
}