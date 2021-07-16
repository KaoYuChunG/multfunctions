package com.example.multifunction.modulos

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.multifunction.R
import com.example.multifunction.modulos.airport.AirportFragment
import com.example.multifunction.modulos.music.MusicFragment


class BroacastReceiverActivity : AppCompatActivity() {

    private lateinit var manager: FragmentManager
    private lateinit var transaction: FragmentTransaction

    private val initFilter = IntentFilter().apply {
        addAction(BroadcastData.BROADCAST_AIRMODEL.toString())
        addAction(BroadcastData.BROADCAST_MUSIC.toString())
        addAction(BroadcastData.BROADCAST_MULTI.toString())
        addAction(BroadcastData.BROADCAST_EXPO.toString())
    }

    private val  mReceiver : BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            transaction = manager.beginTransaction();
            val action = intent.action
            when (action) {
                BroadcastData.BROADCAST_AIRMODEL.toString() ->{
                    transaction.replace(R.id.center, AirportFragment(), "airport")
                }
                BroadcastData.BROADCAST_MUSIC.toString() ->{
                    transaction.replace(R.id.center, MusicFragment(), "music")
                }
//                BroadcastData.BROADCAST_MULTI.toString() ->{textView.text = "MULTIPLICAR"}
//                BroadcastData.BROADCAST_EXPO.toString() ->{textView.text = "EXPOENTE"}
            }
            transaction.commit();
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broacast_receiver)
        setupView()
        setupReceiverNative()
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        when(event?.keyCode) {
            KeyEvent.KEYCODE_BACK->
                Toast.makeText(applicationContext,"buttom back foi preciosnado", Toast.LENGTH_SHORT).show()
            KeyEvent.KEYCODE_VOLUME_UP->
                Toast.makeText(applicationContext,"aumenta o volume !!", Toast.LENGTH_SHORT).show()
            KeyEvent.KEYCODE_VOLUME_DOWN->
                Toast.makeText(applicationContext,"diminui o volume !!", Toast.LENGTH_SHORT).show()
            KeyEvent.KEYCODE_MUTE->
                Toast.makeText(applicationContext,"modo silencioso", Toast.LENGTH_SHORT).show()
        }

        return super.dispatchKeyEvent(event)
    }

    override fun onUserLeaveHint() {
        Toast.makeText(applicationContext,"buttom Home foi preciosnado", Toast.LENGTH_SHORT).show()
        super.onUserLeaveHint()
    }

    private fun setupReceiverNative() {
        manager = getSupportFragmentManager();
    }
    private fun setupView() {
        val btnSomar = findViewById(R.id.btn_somar) as Button
        val btnDividir = findViewById(R.id.btn_dividir) as Button
        val btnExpoente = findViewById(R.id.btn_expoente) as Button
        val btnMultiplicar = findViewById(R.id.btn_multiplicar) as Button

        btnSomar.setOnClickListener{
            Intent().also { intent ->
                intent.setAction(BroadcastData.BROADCAST_AIRMODEL.toString())
                sendBroadcast(intent)
            }
        }

        btnDividir.setOnClickListener{
            Intent().also { intent ->
                intent.setAction(BroadcastData.BROADCAST_MUSIC.toString())
                sendBroadcast(intent)
            }
        }
        btnExpoente.setOnClickListener{
            Intent().also { intent ->
                intent.setAction(BroadcastData.BROADCAST_MULTI.toString())
                sendBroadcast(intent)
            }
        }
        btnMultiplicar.setOnClickListener{
            Intent().also { intent ->
                intent.setAction(BroadcastData.BROADCAST_EXPO.toString())
                sendBroadcast(intent)
            }
        }
    }

    override fun onResume() {
        if(initFilter != null) registerReceiver(mReceiver, initFilter)
        super.onResume()
    }

    override fun onPause() {
        if(mReceiver!= null) unregisterReceiver(mReceiver)
        super.onPause()
    }
}