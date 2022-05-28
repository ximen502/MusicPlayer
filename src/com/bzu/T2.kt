package com.bzu

import org.jaudiotagger.audio.AudioFileIO
import java.io.File
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem

/**
 * 各种播放mp3方法的测试类
 */
class T2 {
    public fun playByMp3Spi() {
//        var file = File(javaClass.getResource("/qc.mp3").path)
//        var inputStream = FileInputStream(file)
//        var player = SpiPlayer(inputStream)
//        player.play()

    }

    /**
     * 不支持mp3
     */
    fun getAudioInfo() {
        var path = javaClass.getResource("/qc.mp3").path
        var file = File(path)
        var ais: AudioInputStream = AudioSystem.getAudioInputStream(file)
        var af = ais.format
        var result = af.toString()
        println(result)
        println("音频总帧数：${ais.frameLength}")
        println("每秒播放的帧数：${af.sampleRate}")
        var length = ais.frameLength / af.sampleRate
        println("音频时长:$length")
        println("音频时长:${length / 60}分钟+${length % 60}秒")
    }

    fun getAudioInfo2() {
        var path = javaClass.getResource("/qc.mp3").path
        var file = File(path)
        var mp3= AudioFileIO.read(file)
        var trackLength = mp3.audioHeader.trackLength
        println("音频时长:$trackLength")
    }


}

fun main(args: Array<String>) {
    val t2 = T2()
//    t2.playByMp3Spi()
//    t2.getAudioInfo()
    t2.getAudioInfo2()

}