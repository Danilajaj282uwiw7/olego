package com.example.olego

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var mediaPlayer1: MediaPlayer? = null
    private var mediaPlayer2: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация двух звуков (файлы должны лежать в res/raw/)
        mediaPlayer1 = MediaPlayer.create(this, R.raw.sound1)
        mediaPlayer2 = MediaPlayer.create(this, R.raw.sound2)

        // После окончания первого звука запускаем второй
        mediaPlayer1?.setOnCompletionListener {
            mediaPlayer2?.start()
        }

        // После окончания второго звука освобождаем ресурсы
        mediaPlayer2?.setOnCompletionListener {
            releasePlayers()
        }

        // Запускаем первый звук автоматически
        mediaPlayer1?.start()
    }

    private fun releasePlayers() {
        mediaPlayer1?.release()
        mediaPlayer2?.release()
        mediaPlayer1 = null
        mediaPlayer2 = null
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayers()
    }
}
