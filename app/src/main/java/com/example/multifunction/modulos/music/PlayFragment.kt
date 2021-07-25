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
import java.io.IOException
import java.lang.IllegalStateException


class PlayFragment : Fragment(), View.OnClickListener,
    MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

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

        return setupView(view)
//        return inflater.inflate(R.layout.fragment_play, container, false)
    }


    private fun setupView(view : View) : View {

        playButton = view.findViewById(R.id.btn_play_pause)
        stopButton = view.findViewById(R.id.btn_stop)
        progressSeekBar = view.findViewById(R.id.progressSeekBar)
        volumeSeekBar = view.findViewById(R.id.volumeSeekBar)

        mediaPlayer = MediaPlayer()
        mediaPlayer.setOnPreparedListener(this)
        mediaPlayer.setOnCompletionListener(this)


        if(getActivity()!=null){
            resources
        }
        val filename : String = "android.resource://" + getActivity()?.packageName +
                "/" + R.raw.audio_bird
        try{
            mediaPlayer.setDataSource(filename)
            mediaPlayer.prepareAsync()
            setButtonTexts(mediaPlayer)
        }catch (e:IllegalArgumentException){
            e.printStackTrace()
        }catch (e:SecurityException){
            e.printStackTrace()
        }catch (e:IllegalStateException){
            e.printStackTrace()
        }catch (e:IOException){
            e.printStackTrace()
        }

        // total time duration of sonng
//        progressSeekBar.max = mediaPlayer.duration
//
//        // set default sound level
//        mediaPlayer.setVolume(25 / 100f, 25 / 100f)
//
//        progressSeekBar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
//            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
//                if(isSeeking) {
//                    mediaPlayer.seekTo(progressSeekBar.progress)
//                }
//            }
//
//            override fun onStartTrackingTouch(p0: SeekBar?) {
//                isSeeking = true
//            }
//
//            override fun onStopTrackingTouch(p0: SeekBar?) {
//                isSeeking = false
//            }
//
//        })
//
//        volumeSeekBar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
//            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
//                // update progress text
//                Toast.makeText(getActivity(),"Volumn: ${volumeSeekBar.progress}%", Toast.LENGTH_SHORT).show()
//
//                // update volumn
//                mediaPlayer.setVolume(progress / 100f, progress / 100f)
//            }
//
//            override fun onStartTrackingTouch(p0: SeekBar?) {}
//            override fun onStopTrackingTouch(p0: SeekBar?) {}
//        })


        // continuously updating progress
//        val thread = Thread(Runnable {
//            while (true) {
//                Thread.sleep(500)
//                if (!isSeeking) {
//                    progressSeekBar.progress = mediaPlayer.currentPosition
//                }
//            }
//        })
//        thread.start()

        return view
    }

    private fun setButtonTexts(mp: MediaPlayer) {
        if(mp.isPlaying){
            val pause = getResources().getDrawable(R.drawable.ic_pause_music)
            playButton.setImageDrawable(pause)
        }else{
            val play = getResources().getDrawable(R.drawable.ic_play_music)
            playButton.setImageDrawable(play)
        }
    }

    private fun startPlayer() {
        // 如果播放完畢，則從頭開始播放
        if(mediaPlayer.currentPosition == mediaPlayer.duration){
//            mediaPlayer.seekTo(0)
        }
        stopButton.isEnabled = true
        mediaPlayer.start()
        setButtonTexts(mediaPlayer)
    }

    private fun pausePlayer() {
        mediaPlayer.pause()
        setButtonTexts(mediaPlayer)
    }

    private fun stopPlayer() {
        if(mediaPlayer.isPlaying){
            mediaPlayer.stop()
            mediaPlayer.seekTo(0)
            progressSeekBar.progress = 0
            mediaPlayer.release()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        stopPlayer()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_play_pause -> {
                if(mediaPlayer.isPlaying){
                    pausePlayer()
                }else{
                    startPlayer()
                }
            }
            R.id.btn_stop -> {
                stopPlayer()
            }
        }
    }

    override fun onPrepared(mp: MediaPlayer) {
        playButton.isEnabled = true
        stopButton.isEnabled = false
        setButtonTexts(mp)
    }

    override fun onCompletion(mp: MediaPlayer) {
        setButtonTexts(mp)
    }
}