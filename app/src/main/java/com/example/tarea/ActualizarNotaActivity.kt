package com.example.tarea

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tarea.databinding.ActivityActualizarNotaBinding

class ActualizarNotaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActualizarNotaBinding
    private lateinit var db: NotasDatabaseHelper
    private var notaId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarNotaBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.layoutActualizarNota) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = NotasDatabaseHelper(this)

        notaId = intent.getIntExtra("nota_id", -1)
        if (notaId == -1) {
            finish()
            return
        }

        val nota = db.getNotaById(notaId)
        if (nota != null) {
            binding.etTitulo.setText(nota.titulo)
            binding.etDescripcion.setText(nota.description)
        }

        binding.ivActualizarNota.setOnClickListener {
            val nuevoTitulo = binding.etTitulo.text.toString()
            val nuevaDescripcion = binding.etDescripcion.text.toString()

            if (nuevoTitulo.isNotEmpty() && nuevaDescripcion.isNotEmpty()) {
                val notaActualizada = Nota(notaId, nuevoTitulo, nuevaDescripcion)
                db.updateNota(notaActualizada)
                Toast.makeText(this, "Nota actualizada", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Por favor llenar los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}