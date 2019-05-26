package com.github.kazukinr.android.sample.ui.exo_player_sample

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.github.kazukinr.android.sample.R
import com.github.kazukinr.android.sample.databinding.ExoPlayerSampleFragmentBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.video.MediaCodecVideoRenderer
import dagger.android.support.DaggerFragment

class ExoPlayerSampleFragment : DaggerFragment() {

    private var binding: ExoPlayerSampleFragmentBinding? = null

    private var player: ExoPlayer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding?.let {
            return it.root
        }

        return DataBindingUtil.inflate<ExoPlayerSampleFragmentBinding>(
            inflater,
            R.layout.exo_player_sample_fragment,
            container,
            false
        ).also {
            binding = it
        }.root
    }

    override fun onResume() {
        super.onResume()

        player = ExoPlayerFactory.newSimpleInstance(requireContext())
        binding?.player?.player = player

        val mediaSource = ProgressiveMediaSource.Factory(
            DefaultDataSourceFactory(requireContext(), "user-agent"),
            DefaultExtractorsFactory()
        ).createMediaSource(Uri.parse("file:///android_asset/movie_sample.mp4"))

        player?.prepare(mediaSource)
        player?.playWhenReady = true
    }

    override fun onPause() {
        super.onPause()

        binding?.player?.player = null
        player?.release()
        player = null
    }
}