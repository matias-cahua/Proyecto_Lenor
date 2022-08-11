package com.example.mi_proyecto.navii1

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import com.example.mi_proyecto.R
import com.example.mi_proyecto.clases.Entities.Producto
import com.example.mi_proyecto.database.appDatabase
import com.example.mi_proyecto.database.productDao
import com.example.mi_proyecto.navii1.FragmentHostArgs.Companion.fromBundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_features.*

// TODO: Rename parameter arguments, choose names that match

class FeaturesFragment : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var producto : Producto
    lateinit var v: View


    private var db : appDatabase? = null
    private var productDao : productDao? = null
    lateinit var edtTest : TextView
    private var idH : Int = 0
    private val PREF_NAME = "myPreferences"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_features, container, false)
        db = appDatabase.getAppDataBase(v.context)
        productDao = db?.productDao()
        setHasOptionsMenu(true)

        return v
    }

    override fun onStart() {
        super.onStart()


        val sharedPref: SharedPreferences = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        idH = sharedPref.getInt("posi", 0)


        Snackbar.make(v, idH.toString(), Snackbar.LENGTH_SHORT).show()

        val product = productDao?.loadProductById(idH)!!
        txt_Marca.setText( product?.marca )
        txt_Modelo.setText( product?.modelo )
        txt_Categoria.setText( product?.tipo.toString() )
        txt_ClaseEE.setText( product?.clase )
        txt_Consumo.setText( product?.consumo.toString() )
        txt_VolRef.setText( product?.volRef.toString() )
        txt_VolCong4.setText( product?.volCong4.toString() )
        txt_VolCong2.setText( product?.volCong2.toString() )
        txt_Estrellas.setText( product?.estrellas )
        txt_Escarcha.setText( product?.escarcha )
        txt_Auto.setText( product?.autonomia.toString() )
        txt_Capa.setText( product?.capaCong.toString() )
        txt_Clima.setText( product?.clima )
        txt_Ruido.setText( product?.ruido.toString() )

    }


}