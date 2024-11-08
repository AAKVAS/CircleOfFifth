package com.example.circleoffifth.utils

import com.example.circleoffifth.data.Chord

object JVMChordSoundManager : ChordSoundPlayer {
    override fun playChord(chord: Chord) {
        TODO("Not yet implemented")
    }
}

actual fun getChordSoundPlayer(): ChordSoundPlayer {
    return JVMChordSoundManager
}