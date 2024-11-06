package com.example.circleoffifth.utils

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import com.example.circleoffifth.R
import com.example.circleoffifth.data.Chord

object ChordSoundManager {
    private lateinit var soundPool: SoundPool
    private lateinit var chordSounds: Map<Chord, Int>
    fun create(context: Context) {
        val attributes: AudioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setAudioAttributes(attributes)
            .setMaxStreams(3)
            .build()

        chordSounds = mapOf(
            Chord.A to soundPool.load(context, R.raw.a, 0),
            Chord.B to soundPool.load(context, R.raw.b, 0),
            Chord.C to soundPool.load(context, R.raw.c, 0),
            Chord.D to soundPool.load(context, R.raw.d, 0),
            Chord.E to soundPool.load(context, R.raw.e, 0),
            Chord.F to soundPool.load(context, R.raw.f, 0),
            Chord.G to soundPool.load(context, R.raw.g, 0),
            Chord.A_FLAT to soundPool.load(context, R.raw.gsharp, 0),
            Chord.B_FLAT to soundPool.load(context, R.raw.asharp, 0),
            Chord.D_FLAT to soundPool.load(context, R.raw.csharp, 0),
            Chord.E_FLAT to soundPool.load(context, R.raw.dsharp, 0),
            Chord.F_SHARP to soundPool.load(context, R.raw.fsharp, 0),
            Chord.A_MINOR to soundPool.load(context, R.raw.am, 0),
            Chord.B_MINOR to soundPool.load(context, R.raw.bm, 0),
            Chord.C_MINOR to soundPool.load(context, R.raw.cm, 0),
            Chord.D_MINOR to soundPool.load(context, R.raw.dm, 0),
            Chord.E_MINOR to soundPool.load(context, R.raw.em, 0),
            Chord.F_MINOR to soundPool.load(context, R.raw.fm, 0),
            Chord.G_MINOR to soundPool.load(context, R.raw.gm, 0),
            Chord.B_FLAT_MINOR to soundPool.load(context, R.raw.amsharp, 0),
            Chord.C_SHARP_MINOR to soundPool.load(context, R.raw.cmsharp, 0),
            Chord.E_FLAT_MINOR to soundPool.load(context, R.raw.dmsharp, 0),
            Chord.F_SHARP_MINOR to soundPool.load(context, R.raw.fmsharp, 0),
            Chord.G_SHARP_MINOR to soundPool.load(context, R.raw.gmsharp, 0),
        )
    }

    fun playChord(chord: Chord) {
        chordSounds[chord]?.let {
            soundPool.play(it, 1f, 1f, 0, 0, 1f)
        }
    }
}