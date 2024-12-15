package com.expertcourse.musicplayer

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.expertcourse.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var uiState: UiState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (application as MusicApp).viewModel

        uiState = if (savedInstanceState == null) {
            viewModel.init()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState.getSerializable(SAVED_STATE_KEY, UiState::class.java) as UiState
            } else {
                savedInstanceState.getSerializable(SAVED_STATE_KEY) as UiState
            }
        }
        updateUi(uiState)


        binding.next.setOnClickListener {
            viewModel.nextTrack()
        }

        binding.previous.setOnClickListener {
            viewModel.previousTrack()
        }

        binding.play.setOnClickListener {
            if (viewModel.isPlay()) {
                uiState = viewModel.pauseTrack()
                updateUi(uiState)
            } else {
                uiState = viewModel.startTrack()
                updateUi(uiState)
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(SAVED_STATE_KEY, uiState)
    }

    private fun updateUi(state: UiState) {
        state.update(binding)
    }

    companion object {
        private const val SAVED_STATE_KEY = "SAVED_STATE_KEY"
    }
}