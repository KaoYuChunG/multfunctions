package com.example.multifunction.modulos.music

import android.animation.ObjectAnimator
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import com.example.multifunction.R


class PlayFragment : Fragment() {

    lateinit var mediaPlayer: MediaPlayer

    lateinit var playButton : ImageView
    lateinit var stopButton : ImageView
    lateinit var progressSeekBar : SeekBar
    lateinit var volumeSeekBar : SeekBar

    private var isSeeking = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_play, container, false)

//        return setupView(view)
        return inflater.inflate(R.layout.fragment_play, container, false)
    }

    private var playButtonClickHandler = View.OnClickListener { view ->

        if(mediaPlayer.isPlaying){
            pausePlayer()
        } else {
            startPlayer()
        }
    }

    private var stopButtonClickHandler = View.OnClickListener { view ->
        stopPlayer()
    }

    private fun setupView(view : View) : View {
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.audio_bird)

        playButton = view.findViewById(R.id.btn_play_pause)
        stopButton = view.findViewById(R.id.btn_stop)
        progressSeekBar = view.findViewById(R.id.progressSeekBar)
        volumeSeekBar = view.findViewById(R.id.volumeSeekBar)

        // total time duration of sonng
        progressSeekBar.max = mediaPlayer.duration

        // set default sound level
        mediaPlayer.setVolume(25 / 100f, 25 / 100f)

        // set listener
        playButton.setOnClickListener(playButtonClickHandler)
        stopButton.setOnClickListener(stopButtonClickHandler)

        progressSeekBar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                if(isSeeking) {
                    mediaPlayer.seekTo(progressSeekBar.progress)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                isSeeking = true
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                isSeeking = false
            }

        })

        volumeSeekBar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                // update progress text
                Toast.makeText(getActivity(),"Volumn: ${volumeSeekBar.progress}%", Toast.LENGTH_SHORT).show()

                // update volumn
                mediaPlayer.setVolume(progress / 100f, progress / 100f)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        mediaPlayer.setOnCompletionListener {
            stopPlayer()
        }


        // continuously updating progress
        val thread = Thread(Runnable {
            while (true) {
                Thread.sleep(500)
                if (!isSeeking) {
                    progressSeekBar.progress = mediaPlayer.currentPosition
                }
            }
        })
        thread.start()

        return view
    }

    private fun startPlayer() {
        // 如果播放完畢，則從頭開始播放
        if(mediaPlayer.currentPosition == mediaPlayer.duration){
            mediaPlayer.seekTo(0)
        }

        mediaPlayer.start()
        val pause = getResources().getDrawable(R.drawable.ic_pause_music)
        playButton.setImageDrawable(pause)
    }

    private fun pausePlayer() {
        mediaPlayer.pause()

        val pause = getResources().getDrawable(R.drawable.ic_play_music)
        playButton.setImageDrawable(pause)
    }

    private fun stopPlayer() {
        mediaPlayer.pause()
        mediaPlayer.seekTo(0)

        progressSeekBar.progress = 0
    }
}