package com.example.bibliomayaya

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class usuarios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_usuarios)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun irOpcionesLibros(view: View){
        var intent= Intent(application,menuOpcionesLibro::class.java)
        startActivity(intent)
    }

    fun irInicio(view: View){
        var intent= Intent(application,MainActivity::class.java)
        startActivity(intent)
    }

    fun volver(view: View){
        var intent= Intent(application,MainActivity::class.java)
        startActivity(intent)
    }

    fun irMultas(view: View){
        var intent= Intent(application,multas::class.java)
        startActivity(intent)
    }

    fun irUsuarios(view: View){
        var intent= Intent(application,usuarios::class.java)
        startActivity(intent)
    }

    fun irPrestamos(view: View){
        var intent= Intent(application,prestamos::class.java)
        startActivity(intent)
    }


}