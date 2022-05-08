package com.bzu

import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JFrame
import javax.swing.Timer

class Player() : JFrame() {
    var lyricPan:LyricPan = LyricPan()

    init {
        add(lyricPan, BorderLayout.CENTER)

        val timer = Timer(100, TimerListener())
        timer.start()
    }

    private inner class TimerListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            lyricPan.setYAxis(lyricPan.getYAxis() - 5)
        }
    }
}