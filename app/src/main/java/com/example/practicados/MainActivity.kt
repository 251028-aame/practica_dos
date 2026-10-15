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
    private lateinit var btnTemaGVA: Button
    private lateinit var pbCarga: ProgressBar
    private lateinit var tvMensaje: TextView

    private var oscuroGVA = false
    private var contadorAsistentes = 0 // Contador para las asistencias

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
        btnTemaGVA = findViewById(R.id.btnTema)
        pbCarga = findViewById(R.id.pbCarga)

        pbCarga.visibility = View.GONE

        // Listeners de los botones
        btnRegistrar.setOnClickListener { registrarOpcion() }
        btnLimpiar.setOnClickListener { limpiar() }
        btnTemaGVA.setOnClickListener { cambiarTemaGVA() }
    }

    private fun registrarOpcion() {
        // Si el botón dice "Registrar otro", lo regresamos al estado inicial
        if (btnRegistrar.text.toString() == getString(R.string.btn_registrar_otroGVA)) {
            limpiar()
            return
        }

        val nombre = etxNombre.text.toString().trim()
        val matricula = etxMatricula.text.toString().trim()

        // 7.1 · Textos de validación sacados a strings.xml
        if (nombre.isEmpty()) {
            Toast.makeText(this, getString(R.string.val_nombre_vacioGVA), Toast.LENGTH_SHORT).show()
            return
        } else if (nombre.length < 3) {
            Toast.makeText(this, getString(R.string.val_nombre_cortoGVA), Toast.LENGTH_SHORT).show()
            return
        }

        if (matricula.isEmpty()) {
            Toast.makeText(this, getString(R.string.val_matricula_vaciaGVA), Toast.LENGTH_SHORT).show()
            return
        } else if (matricula.length != 7) {
            Toast.makeText(this, getString(R.string.val_matricula_largoGVA), Toast.LENGTH_SHORT).show()
            return
        }

        pbCarga.visibility = View.VISIBLE
        tvMensaje.visibility = View.GONE
        btnRegistrar.isEnabled = false

        // Simulamos el registro exitoso
        contadorAsistentes++

        // 7.3 y Punto Extra: Uso de strings con marcadores de posición para el mensaje de éxito
        tvMensaje.text = getString(R.string.msg_registradoGVA, nombre, matricula, contadorAsistentes)
        tvMensaje.visibility = View.VISIBLE
        pbCarga.visibility = View.GONE

        // Cambiamos el texto del botón a "Registrar otro"
        btnRegistrar.text = getString(R.string.btn_registrar_otroGVA)
        btnRegistrar.isEnabled = true

        etxNombre.isEnabled = false
        etxMatricula.isEnabled = false
    }

    private fun limpiar() {
        etxNombre.text.clear()
        etxMatricula.text.clear()

        etxNombre.isEnabled = true
        etxMatricula.isEnabled = true

        // 7.2 · Textos de limpiar sacados a strings.xml
        tvMensaje.text = getString(R.string.msg_inicialGVA)
        tvMensaje.visibility = View.VISIBLE
        btnRegistrar.text = getString(R.string.btn_registrarGVA)

        pbCarga.visibility = View.GONE
        btnRegistrar.isEnabled = true
        etxNombre.requestFocus()
    }

    private fun cambiarTemaGVA() {
        oscuroGVA = !oscuroGVA

        if (oscuroGVA) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            btnTemaGVA.text = getString(R.string.btn_tema_claroGVA)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            btnTemaGVA.text = getString(R.string.btn_tema_oscuroGVA)
        }
    }
}