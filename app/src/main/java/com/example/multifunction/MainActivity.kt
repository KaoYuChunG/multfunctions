package com.example.multifunction

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multifunction.model.MainItem
import com.example.multifunction.modulos.BroacastReceiverActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById<RecyclerView>(R.id.main_rv)
        val mainItems: MutableList<MainItem> = ArrayList()

        mainItems.add(MainItem(1, R.string.label_broadcast_receiver, R.color.teal_200))
        mainItems.add(MainItem(1, R.string.label_broadcast_receiver, R.color.teal_200))

        rvMain.setLayoutManager(GridLayoutManager(this, 2))
        val adapter = MainAdapter(mainItems)
        opcoes(adapter)
    }

    private fun opcoes(adapter: MainAdapter) {
        adapter.setListener(object : OnItemClickListener {
            override fun onClick(id: Int) {
                when (id) {
                    1 -> startActivity(
                        Intent(this@MainActivity, BroacastReceiverActivity::class.java)
                    )
                }
            }
        })
        rvMain.adapter = adapter
    }
    private class MainAdapter(mainItems: List<MainItem>) :
        RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
        private val mainItems: List<MainItem>
        private var listener: OnItemClickListener? = null

        fun setListener(listener: OnItemClickListener?) {
            this.listener = listener
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

            val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val mainItemCurrent: MainItem = mainItems[position]
            holder.bind(mainItemCurrent)
        }

        override fun getItemCount(): Int {
            return mainItems.size
        }

        // Entenda como sendo a VIEW DA CELULA que está dentro do RecyclerView
        private inner class MainViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
            fun bind(item: MainItem) {
                val txtName = itemView.findViewById<TextView>(R.id.item_text_name)
                val btnGeral = itemView.findViewById<View>(R.id.btn_geral) as LinearLayout
                btnGeral.setOnClickListener { view: View? ->
                    listener!!.onClick(
                        item.id
                    )
                }
                btnGeral.setBackgroundColor(item.color)
                txtName.setText(item.textStringIdval)
            }
        }

        init {
            this.mainItems = mainItems
        }
    }
}