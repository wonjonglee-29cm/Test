package com.wonjong.test

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.wonjong.test.databinding.ActivityMainBinding
import com.wonjong.test.view.YouTubePlayerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.url.collect { url ->
                binding.youtube.play(
                    fragmentManager = supportFragmentManager,
                    videoId = url,
                    initializedListener = object : YouTubePlayerView.OnInitializedListener {
                        override fun onInitializationSuccess(provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean) {

                        }

                        override fun onInitializationFailure(provider: YouTubePlayer.Provider, result: YouTubeInitializationResult) {

                        }
                    },
                    playbackEventListener = object : YouTubePlayer.PlaybackEventListener {
                        override fun onPlaying() {

                        }

                        override fun onPaused() {

                        }

                        override fun onStopped() {

                        }

                        override fun onBuffering(p0: Boolean) {

                        }

                        override fun onSeekTo(p0: Int) {

                        }
                    })
            }
        }
    }
}