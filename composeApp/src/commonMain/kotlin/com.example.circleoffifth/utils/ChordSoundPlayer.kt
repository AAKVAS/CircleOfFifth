package com.example.circleoffifth.utils

import com.example.circleoffifth.data.Chord

interface ChordSoundPlayer {
    fun playChord(chord: Chord)
}

expect fun getChordSoundPlayer(): ChordSoundPlayer