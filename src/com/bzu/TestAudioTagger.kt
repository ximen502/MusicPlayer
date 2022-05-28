package com.bzu

import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.tag.FieldKey
import java.io.File

/**
 * JAudioTagger jar的api测试
 */
class TestAudioTagger {
    fun testJAudioTagger() {
        var path = javaClass.getResource("/my.mp3").path
        var file = File(path)
        var af = AudioFileIO.read(file)

        var tag = af.tag
        var ah = af.audioHeader
        println(ah.trackLength)
//        println(ah.sampleRate)
        println(ah.sampleRateAsNumber)

        println("===============================")
        println(tag.getFirst(FieldKey.ARTIST))
        println(tag.getFirst(FieldKey.ALBUM))
        println(tag.getFirst(FieldKey.TITLE))
        println(tag.getFirst(FieldKey.COMMENT))
        println(tag.getFirst(FieldKey.YEAR))
        println(tag.getFirst(FieldKey.TRACK))
        println(tag.getFirst(FieldKey.DISC_NO))
        println(tag.getFirst(FieldKey.COMPOSER))
        println(tag.getFirst(FieldKey.ARTIST_SORT))
    }
}

fun main(args: Array<String>) {
    var t = TestAudioTagger();
    t.testJAudioTagger()
}