package com.example.multifunction.modulos.airport

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.multifunction.R

class AirportFragment : Fragment() {
    private lateinit var imgAir: ImageView

    private val initFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)

    private val  mReceiver : BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val state = intent!!.getBooleanExtra("state", false)
            Log.i("KAO", "air " + state)
            if(state) {
                on()
            }else {
                off()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_airport, container, false)
        return setupView(view)
    }

    private fun on() {
        val on = getResources().getDrawable(R.drawable.ic_airport_on)
        imgAir.setImageDrawable(on)
    }

    private fun off() {
        val off = getResources().getDrawable(R.drawable.ic_airplane_off)
        imgAir.setImageDrawable(off)
    }

    private fun setupView(view : View) : View{
        imgAir = view.findViewById(R.id.btn_modelo_airport)
        return view
    }

    override fun onResume() {
        if(initFilter != null) requireActivity().registerReceiver(mReceiver, initFilter)
        super.onResume()
    }

    override fun onPause() {
        if(mReceiver!= null) requireActivity().unregisterReceiver(mReceiver)
        super.onPause()
    }
}