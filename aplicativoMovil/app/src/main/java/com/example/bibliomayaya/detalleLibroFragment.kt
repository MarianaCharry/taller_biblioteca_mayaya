package com.example.bibliomayaya

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bibliomayaya.config.config
import com.example.bibliomayaya.models.libro
import com.google.gson.Gson

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [detalleLibroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class detalleLibroFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //definir las variables
    private lateinit var lblTitulo: TextView
    private lateinit var lblAutor: TextView
    private lateinit var lblISBN: TextView
    private lateinit var lblGenero: TextView
    private lateinit var lblDisponible: TextView
    private lateinit var lblOcupado: TextView

    private lateinit var btnEditar: Button
    private lateinit var btnEliminar: Button

    private var id:String="1435caae-3ed3-4455-98f7-32258fe0673f"

    fun consultarLibro(){
        if (id!=""){

            var request= JsonObjectRequest(
                Request.Method.GET, //método de la petición
                config.urlLibro+id, //url
                null, //parámetros
                {response->
                    val gson= Gson()
                    val libro: libro =gson.fromJson(response.toString(), libro::class.java)
                    lblTitulo.setText(libro.autor_libro)
                    lblAutor.setText(libro.autor_libro)
                    lblISBN.setText(libro.isbn_libro)
                    lblGenero.setText(libro.genero_libro)
                    lblDisponible.setText(libro.numero_ejemplares_disponibles)
                    lblOcupado.setText(libro.numero_ejemplares_ocupados)


                },
                {error->
                    Toast.makeText(
                        context,
                        "Error al consultar",
                        Toast.LENGTH_LONG
                    ).show()
                }
            )
            var queue= Volley.newRequestQueue(context)
            queue.add(request)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_detalle_libro, container, false)
        lblTitulo=view.findViewById(R.id.lblTituloDetalle)
        lblAutor=view.findViewById(R.id.lblAutorDetalle)
        lblISBN=view.findViewById(R.id.lblISBNDetalle)
        lblGenero=view.findViewById(R.id.lblGeneroDetalle)
        lblDisponible=view.findViewById(R.id.lblDisponiblesDetalle)
        lblOcupado=view.findViewById(R.id.lblOcupadosDetalle)
        btnEditar=view.findViewById(R.id.btnEditar)
        btnEditar.setOnClickListener{editarLibro()}
        btnEliminar=view.findViewById(R.id.btnEliminar)
        btnEliminar.setOnClickListener{eliminarLibro()}
        consultarLibro()
        return view

    }

    fun editarLibro(){

    }


    fun eliminarLibro(){

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment detalleLibroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            detalleLibroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}