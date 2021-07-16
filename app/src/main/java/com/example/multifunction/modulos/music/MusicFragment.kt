package com.example.multifunction.modulos.music

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.multifunction.R

class MusicFragment : Fragment(), View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private lateinit var btnPlayPause: Button
    private lateinit var mediaPlay: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_music, container, false)

        initView(view)
        return view
    }

    private fun initView(view: View) {
        btnPlayPause = view.findViewById(R.id.btn_play_pause)
        btnPlayPause.setOnClickListener(this)
        btnPlayPause.isEnabled = false

        mediaPlay = MediaPlayer()
        mediaPlay.setOnPreparedListener(this)
        mediaPlay.setOnCompletionListener(this)


//        val filename = "android.resource://"+ getPackageName() + "/" + R.raw.bgm_healing02

//        try {
//            mediaPlay.setDataSource(this, Uri.parse(filename))
        }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    override fun onPrepared(mp: MediaPlayer?) {
        TODO("Not yet implemented")
    }

    override fun onCompletion(mp: MediaPlayer?) {
        TODO("Not yet implemented")
    }
}
//    https://github.com/slamdon/kotlin-playground/blob/master/19-LittleBirdSound/app/src/main/java/devdon/com/recorder/PlayerActivity.kt




