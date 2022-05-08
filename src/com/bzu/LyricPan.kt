package com.bzu

import com.bzu.util.FileUtil
import java.awt.*
import javax.swing.BorderFactory
import javax.swing.JPanel

class LyricPan() : JPanel() {
    private var lrcFont: Font
    private var lrc: String
    private var lrcArr: Array<String>
    private var yAxis: Int

    init {
        border = BorderFactory.createLineBorder(Color.RED, 1)
        lrcFont = Font("宋体", Font.PLAIN, 16)
        lrc = FileUtil.readFileSdcard(javaClass.getResource("/kdqg.lrc").path)
        lrcArr = lrc.split(System.getProperty("line.separator").toRegex()).toTypedArray()
        yAxis = 20
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        var g2 = g as Graphics2D
        g2?.let {

            it.font = lrcFont
            for (i in lrcArr.indices) {
                var w = it.fontMetrics.stringWidth(lrcArr[i])
                var x = (width - w) / 2
//                println(lrcArr[i])
                if (i == 3) {
                    it.color = Color.RED
                } else {
                    it.color = Color.BLACK
                }
                it.drawString(lrcArr[i], x, yAxis + 25 * i)
            }
        }
    }

    override fun getPreferredSize(): Dimension {
        return Dimension(300, 250)
    }

    fun setYAxis(y: Int) {
        yAxis = y
        repaint()
    }

    fun getYAxis(): Int = yAxis
}