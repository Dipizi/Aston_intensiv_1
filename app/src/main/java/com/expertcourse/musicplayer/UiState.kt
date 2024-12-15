package com.expertcourse.musicplayer

import androidx.annotation.DrawableRes
import com.expertcourse.musicplayer.databinding.ActivityMainBinding
import java.io.Serializable

interface UiState : Serializable {

    fun update(binding: ActivityMainBinding)

    abstract class Abstract(
        @DrawableRes private val idRes: Int
    ) : UiState {

        override fun update(binding: ActivityMainBinding) {
            binding.play.setImageResource(idRes)
        }
    }

    object Play : Abstract(R.drawable.play_36)

    object Pause : Abstract(R.drawable.pause_36)
}