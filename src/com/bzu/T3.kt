package com.bzu

import javazoom.jl.decoder.JavaLayerException
import javazoom.jl.player.Player
import org.jaudiotagger.audio.AudioFileIO
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import javax.swing.*

/**
 * jl.jar播放mp3
 * api说明：
 * void play()      播放
 * boolean play(int frames) 只播放指定数量的帧数
 * boolean isComplete() 是否播放完毕
 * int getPosition()    返回当前播放位置，单位毫秒
 */
class T3 {
    var inputStream: FileInputStream
    var bis: BufferedInputStream
    var mPlayer: Player
    var mPosition = 0
    var jpb = JProgressBar()
    var mAudioLength = 0

    init {
        ui()
        inputStream = FileInputStream(javaClass.getResource("/qc.mp3").path)
        bis = BufferedInputStream(inputStream)
        mPlayer = Player(bis)
    }

    private fun ui() {
        val window = JFrame()
        window.apply {
            preferredSize = Dimension(500, 380)
            size = preferredSize
            setLocationRelativeTo(null)
            isUndecorated = false // 参数为false，下面的setBackground将没有效果
            background = Color(0x0, 0, 0, 0xff)
            isVisible = true
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            title = "mp3 play"
            //iconImage =
        }
        val panel = JPanel(FlowLayout())
        val play = JButton("Play")
        val previous = JButton("<<<")
        val next = JButton(">>>")
        val pause = JButton("Pause")
        panel.add(previous)
        panel.add(play)
        panel.add(pause)
        panel.add(next)
        jpb.isStringPainted = true
        jpb.value = 0
        jpb.maximum = 1000
        window.add(jpb, BorderLayout.NORTH)

        window.add(panel, BorderLayout.CENTER)

        play.addActionListener {
            println("-----")
            makePlayer("")
            Thread() {
                mPlayer.play()
            }.start()

            Thread() {
                while (true) {
                    println("mPlayer.position:${mPlayer.position}, mAudioLength:$mAudioLength")
                    var progress = (mPlayer.position / 1000f / mAudioLength * 1000f).toInt()
                    println(progress)
                    SwingUtilities.invokeLater(Runnable {
                        jpb.value = progress
                    })
                    Thread.sleep(1000)
                }
            }.start()
//            mPlayer.close()
        }

        pause.addActionListener {
            mPosition =mPlayer.position
            println(mPlayer.position)
            mPlayer.close()
        }
    }

    fun makePlayer(path:String): Player {
        inputStream = FileInputStream(javaClass.getResource("/qc.mp3").path)
        bis = BufferedInputStream(inputStream)
        mPlayer = Player(bis)
        return mPlayer
    }

    private fun play() {
        //使用jl1.0.jar实现播放mp3
        //http://www.java2s.com/Code/Jar/j/Downloadjl10jar.htm
        try {
            val inputStream = FileInputStream(javaClass.getResource("/qc.mp3").path)
            val stream = BufferedInputStream(inputStream)
            val player = Player(stream)
            player.play()
        } catch (e: JavaLayerException) {
            e.printStackTrace()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    fun testJAudioTagger() {
        var path = javaClass.getResource("/qc.mp3").path
        var file = File(path)
        var af = AudioFileIO.read(file)

        var tag = af.tag
        var ah = af.audioHeader
        println(ah.trackLength)
        println(ah.sampleRate)
        println(ah.sampleRateAsNumber)
        mAudioLength = ah.trackLength

    }
}

fun main(args: Array<String>) {
    var t3 = T3()
    t3.testJAudioTagger()
}