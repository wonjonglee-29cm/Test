package com.wonjong.test.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT
import com.google.android.youtube.player.YouTubePlayerAndroidxFragment
import com.wonjong.test.R

class YouTubePlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    var onInitializedListener: OnInitializedListener? = null
    private val youTubePlayerAndroidxFragment = YouTubePlayerAndroidxFragment()

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_youtube_player, this, true)
    }

    fun play(
        fragmentManager: FragmentManager,
        videoId: String,
        initializedListener: OnInitializedListener? = null,
        playbackEventListener: YouTubePlayer.PlaybackEventListener? = null
    ) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, youTubePlayerAndroidxFragment)
            .commitAllowingStateLoss()

        initializedListener?.let { this.onInitializedListener = it }

        youTubePlayerAndroidxFragment.initialize(
            javaClass.simpleName,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    player: YouTubePlayer,
                    wasRestored: Boolean
                ) {
                    if (!wasRestored) {
                        player.cueVideo(videoId)
                        player.fullscreenControlFlags = FULLSCREEN_FLAG_CUSTOM_LAYOUT
                        player.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL)
                        player.setShowFullscreenButton(false)
                        playbackEventListener?.let { player.setPlaybackEventListener(it) }
                    }
                    onInitializedListener?.onInitializationSuccess(provider, player, wasRestored)
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    result: YouTubeInitializationResult
                ) {
                    onInitializedListener?.onInitializationFailure(provider, result)
                }
            })
    }

    interface OnInitializedListener {
        fun onInitializationSuccess(
            provider: YouTubePlayer.Provider,
            player: YouTubePlayer,
            wasRestored: Boolean
        )

        fun onInitializationFailure(
            provider: YouTubePlayer.Provider,
            result: YouTubeInitializationResult
        )
    }
}