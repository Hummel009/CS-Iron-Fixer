package com.github.hummel.csiron

import com.formdev.flatlaf.FlatLightLaf
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme
import java.awt.BorderLayout
import java.awt.EventQueue
import java.awt.Font
import java.awt.GridLayout
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.util.*
import java.util.Timer
import javax.imageio.ImageIO
import javax.swing.*
import javax.swing.border.EmptyBorder
import kotlin.random.Random

fun main() {
	FlatLightLaf.setup()
	EventQueue.invokeLater {
		try {
			UIManager.setLookAndFeel(FlatGitHubDarkIJTheme())
			val frame = GUI()
			frame.isVisible = true
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
}

class GUI : JFrame() {
	private var ironTimerTask: IronTimerTask
	private var enabled: Boolean = false

	init {
		title = "Hummel009's CS Iron Fixer"
		defaultCloseOperation = EXIT_ON_CLOSE
		setBounds(100, 100, 640, 640)

		val contentPanel = JPanel()
		contentPanel.border = EmptyBorder(5, 5, 5, 5)
		contentPanel.layout = BorderLayout(0, 0)
		contentPanel.layout = GridLayout(0, 1, 0, 0)
		contentPane = contentPanel

		val timer1 = Timer()
		val numberLabel1 = JLabel()
		numberLabel1.horizontalAlignment = JLabel.CENTER
		numberLabel1.font = Font("Arial", Font.BOLD, 48)

		ironTimerTask = IronTimerTask(numberLabel1, 25, 30)
		timer1.scheduleAtFixedRate(ironTimerTask, 0, 250)

		val timer2 = Timer()
		val numberLabel2 = JLabel()
		numberLabel2.horizontalAlignment = JLabel.CENTER
		numberLabel2.font = Font("Arial", Font.BOLD, 48)

		ironTimerTask = IronTimerTask(numberLabel2, 5, 10)
		timer2.scheduleAtFixedRate(ironTimerTask, 0, 250)

		val textLabel = JLabel("Утюг выключен")
		textLabel.horizontalAlignment = JLabel.CENTER
		textLabel.font = Font("Arial", Font.BOLD, 24)
		val imageLabel = JLabel()
		val imageStream1 = this::class.java.getResourceAsStream("/image.jpg")
		val imageStream2 = this::class.java.getResourceAsStream("/image_hover.jpg")
		val originalImage1 = ImageIO.read(imageStream1)
		val originalImage2 = ImageIO.read(imageStream2)
		val imageIcon1 = ImageIcon(originalImage1)
		val imageIcon2 = ImageIcon(originalImage2)
		imageLabel.icon = imageIcon1
		imageLabel.horizontalAlignment = JLabel.CENTER

		imageLabel.addMouseListener(object : MouseAdapter() {
			override fun mouseEntered(e: MouseEvent) {
				imageLabel.icon = imageIcon2
			}

			override fun mouseExited(e: MouseEvent) {
				imageLabel.icon = imageIcon1
			}

			override fun mouseClicked(e: MouseEvent) {
				if (enabled) {
					textLabel.text = "Утюг выключен"

					contentPanel.remove(numberLabel2)
					contentPanel.remove(imageLabel)
					contentPanel.remove(textLabel)

					contentPanel.add(numberLabel1)
					contentPanel.add(imageLabel)
					contentPanel.add(textLabel)
				} else {
					textLabel.text = "Утюг включен"

					contentPanel.remove(numberLabel1)
					contentPanel.remove(imageLabel)
					contentPanel.remove(textLabel)

					contentPanel.add(numberLabel2)
					contentPanel.add(imageLabel)
					contentPanel.add(textLabel)
				}
				enabled = !enabled
			}
		})

		contentPanel.add(numberLabel1)
		contentPanel.add(imageLabel)
		contentPanel.add(textLabel)

		setLocationRelativeTo(null)
	}
}

class IronTimerTask(private val numberLabel: JLabel, private val from: Int, private val to: Int) : TimerTask() {
	override fun run() {
		val randomNumber = Random.nextInt(from, to)
		numberLabel.text = "Current input lag: $randomNumber"
	}
}