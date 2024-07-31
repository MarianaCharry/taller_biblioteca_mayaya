package com.example.bibliomayaya

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class menuOpcionesLibro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_opciones_libro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun irGuardarLibro(view: View){
        var intent= Intent(application,agregarLibro::class.java)
        startActivity(intent)
    }

    fun volver(view: View){
        var intent= Intent(application,MainActivity::class.java)
        startActivity(intent)
    }

    fun irDetalleLibro(view: View){
        var intent= Intent(application,contenedorDetalleLibro::class.java)
        startActivity(intent)
    }

    fun irOpcionesLibros(view: View){
        var intent= Intent(application,menuOpcionesLibro::class.java)
        startActivity(intent)
    }

    fun irInicio(view: View){
        var intent= Intent(application,MainActivity::class.java)
        startActivity(intent)
    }

    fun irSoporteAyudaLibros(view: View){
        var intent= Intent(application,ayudaLibros::class.java)
        startActivity(intent)
    }

    fun irListaLibro(view: View){
        var intent= Intent(application,contenedorListaLibro::class.java)
        startActivity(intent)
    }
}