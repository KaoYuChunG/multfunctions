package com.example.multifunction.modulos.music

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.multifunction.R
import com.example.multifunction.modulos.BroadcastData


class MusicFragment : Fragment(), View.OnClickListener{

    private lateinit var btnPlay: Button
    private lateinit var btnRecord: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_music, container, false)

        initView(view)
        childFragment(PlayFragment())
        return view
    }

    private fun initView(view: View) {
        btnPlay = view.findViewById(R.id.btn_play)
        btnRecord = view.findViewById(R.id.btn_recorder)
        btnPlay.setOnClickListener(this)
        btnRecord.setOnClickListener(this)
    }

    private fun childFragment(fragment : Fragment) {
        val fragment: Fragment = fragment
        val transaction: FragmentTransaction = getChildFragmentManager().beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        transaction.addToBackStack(null)
        transaction.replace(R.id.music_center, fragment)
        transaction.commit()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_play -> childFragment(PlayFragment())
            R.id.btn_recorder -> childFragment(RecorderFragment())
        }
    }

}
//    https://github.com/slamdon/kotlin-playground/blob/master/19-LittleBirdSound/app/src/main/java/devdon/com/recorder/PlayerActivity.kt




