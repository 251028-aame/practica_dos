package com.example.practicados

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Variables de la interfaz
    private lateinit var etxNombre: EditText
    private lateinit var etxMatricula: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnTemaAAME: Button
    private lateinit var pbCarga: ProgressBar
    private lateinit var tvMensaje: TextView

    private var oscuroAAME = false
    private var contadorAsistentes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Enlace de vistas con el XML (findViewById)
        tvMensaje = findViewById(R.id.tvMensaje)
        etxNombre = findViewById(R.id.etxNombre)
        etxMatricula = findViewById(R.id.etxMatricula)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnTemaAAME = findViewById(R.id.btnTema)
        pbCarga = findViewById(R.id.pbCarga)

        pbCarga.visibility = View.GONE

        // Listeners de los botones
        btnRegistrar.setOnClickListener { registrarOpcion() }
        btnLimpiar.setOnClickListener { limpiar() }
        btnTemaAAME.setOnClickListener { cambiarTemaAAME() }
    }

    private fun registrarOpcion() {

        if (btnRegistrar.text.toString() == getString(R.string.btn_registrar_otroAAME)) {
            limpiar()
            return
        }

        val nombre = etxNombre.text.toString().trim()
        val matricula = etxMatricula.text.toString().trim()


        if (nombre.isEmpty()) {
            Toast.makeText(this, getString(R.string.val_nombre_vacioAAME), Toast.LENGTH_SHORT).show()
            return
        } else if (nombre.length < 3) {
            Toast.makeText(this, getString(R.string.val_nombre_cortoAAME), Toast.LENGTH_SHORT).show()
            return
        }

        if (matricula.isEmpty()) {
            Toast.makeText(this, getString(R.string.val_matricula_vaciaAAME), Toast.LENGTH_SHORT).show()
            return
        } else if (matricula.length != 7) {
            Toast.makeText(this, getString(R.string.val_matricula_largoAAME), Toast.LENGTH_SHORT).show()
            return
        }

        pbCarga.visibility = View.VISIBLE
        tvMensaje.visibility = View.GONE
        btnRegistrar.isEnabled = false

        // Simulamos el registro exitoso
        contadorAsistentes++


        tvMensaje.text = getString(R.string.msg_registradoAAME, nombre, matricula, contadorAsistentes)
        tvMensaje.visibility = View.VISIBLE
        pbCarga.visibility = View.GONE

        // Cambiamos el texto del botón a "Registrar otro"
        btnRegistrar.text = getString(R.string.btn_registrar_otroAAME)
        btnRegistrar.isEnabled = true

        etxNombre.isEnabled = false
        etxMatricula.isEnabled = false
    }

    private fun limpiar() {
        etxNombre.text.clear()
        etxMatricula.text.clear()

        etxNombre.isEnabled = true
        etxMatricula.isEnabled = true

        tvMensaje.text = getString(R.string.msg_inicialAAME)
        tvMensaje.visibility = View.VISIBLE
        btnRegistrar.text = getString(R.string.btn_registrarAAME)

        pbCarga.visibility = View.GONE
        btnRegistrar.isEnabled = true
        etxNombre.requestFocus()
    }

    private fun cambiarTemaAAME() {
        oscuroAAME = !oscuroAAME

        if (oscuroAAME) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            btnTemaAAME.text = getString(R.string.btn_tema_claroAAME)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            btnTemaAAME.text = getString(R.string.btn_tema_oscuroAAME)
        }
    }
}