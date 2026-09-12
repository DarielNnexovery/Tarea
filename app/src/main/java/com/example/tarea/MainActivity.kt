package com.example.tarea

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tarea.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var db : NotasDatabaseHelper
    private lateinit var notasAdaptador : notasAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = NotasDatabaseHelper(this)

        notasAdaptador = notasAdaptador(db.getAllNotas(), this)
        binding.notasRs.layoutManager = LinearLayoutManager(this)
        binding.notasRs.adapter = notasAdaptador
        binding.FABAgregarNota.setOnClickListener {
            startActivity(Intent(applicationContext, AgregarNota::class.java ))

        }
    }

    override fun onResume() {
        super.onResume()
        notasAdaptador.refrescarLista(db.getAllNotas())
    }

}