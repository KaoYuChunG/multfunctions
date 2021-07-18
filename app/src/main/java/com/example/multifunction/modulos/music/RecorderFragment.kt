package com.example.multifunction.modulos.music

import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.multifunction.R
import java.io.File


class RecorderFragment : Fragment() {

    var soundFile: File? = null
    var isRecording = false

    lateinit var playButton: ImageView
    lateinit var recordButton: ImageView

    lateinit var recorder: MediaRecorder
    var player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setupView()
        return inflater.inflate(R.layout.fragment_recorder, container, false)
    }

    private fun setupView() {
//        recordButton.setOnClickListener(recordButtonClickHandler)
//        playButton.setOnClickListener(playButtonClickHandler)
    }

}