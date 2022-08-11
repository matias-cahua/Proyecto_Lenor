package com.example.mi_proyecto.navii1

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.mi_proyecto.R
import com.example.mi_proyecto.clases.Entities.Producto
import com.example.mi_proyecto.database.appDatabase
import com.example.mi_proyecto.database.productDao
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_features.*
import kotlinx.android.synthetic.main.fragment_new_prod.*
import kotlinx.android.synthetic.main.fragment_new_prod.textAuto
import kotlinx.android.synthetic.main.fragment_new_prod.textCapa
import kotlinx.android.synthetic.main.fragment_new_prod.textCategoria
import kotlinx.android.synthetic.main.fragment_new_prod.textClase
import kotlinx.android.synthetic.main.fragment_new_prod.textClima
import kotlinx.android.synthetic.main.fragment_new_prod.textConsumo
import kotlinx.android.synthetic.main.fragment_new_prod.textEscar
import kotlinx.android.synthetic.main.fragment_new_prod.textEstre
import kotlinx.android.synthetic.main.fragment_new_prod.textMarca
import kotlinx.android.synthetic.main.fragment_new_prod.textModelo
import kotlinx.android.synthetic.main.fragment_new_prod.textVolCong
import kotlinx.android.synthetic.main.fragment_new_prod.textVolCong2
import kotlinx.android.synthetic.main.fragment_new_prod.textVolRefri

// TODO: Rename parameter arguments, choose names that match

lateinit var v: View
lateinit var btnSave: Button
private var db : appDatabase? = null
private var productDao : productDao? = null
lateinit var edtTest : TextView
private var idE : Int = 0
private val PREF_NAME = "myPreferences"

class EditFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_edit, container, false)
        db = appDatabase.getAppDataBase(v.context)
        productDao = db?.productDao()
        btnSave = v.findViewById(R.id.btn_save)
        return v
    }

    override fun onStart() {
        super.onStart()
        val sharedPref: SharedPreferences = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        idE = sharedPref.getInt("posi", 0)


        val product = productDao?.loadProductById(idE)!!

        textMode.setText( product?.modelo )
        textMarca.setText( product?.marca )
        textModelo.setText( product?.modelo )
        textCategoria.setText( product?.tipo.toString() )
        textClase.setText( product?.clase )
        textConsumo.setText( product?.consumo.toString() )
        textVolRefri.setText( product?.volRef.toString() )
        textVolCong.setText( product?.volCong4.toString() )
        textVolCong2.setText( product?.volCong2.toString() )
        textEstre.setText( product?.estrellas )
        textEscar.setText( product?.escarcha )
        textAuto.setText( product?.autonomia.toString() )
        textCapa.setText( product?.capaCong.toString() )
        textClima.setText( product?.clima )
        textRuido.setText( product?.ruido.toString() )



        btnSave.setOnClickListener{
            val productos: MutableList<Producto> = productDao?.loadAllProducts() as MutableList<Producto>
            var product = Producto(0,textMarca.text.toString(), textModelo.text.toString(),textCategoria.text.toString(),
                textClase.text.toString(),textConsumo.text.toString().toInt(),textVolRefri.text.toString().toInt(),
                textVolCong.text.toString().toInt(),textVolCong2.text.toString().toInt(),textEstre.text.toString(),textEscar.text.toString(),
                textAuto.text.toString().toInt(),textCapa.text.toString().toDouble(),textClima.text.toString(),52,
                "https://i.imgur.com/WQ25QxVundefined.png" )

            product.id = idE
            productDao?.updateProduct(product)


            textMarca.text.clear()
            textModelo.text.clear()
            textCategoria.text.clear()
            textClase.text.clear()
            textConsumo.text.clear()
            textVolRefri.text.clear()
            textVolCong.text.clear()
            textVolCong2.text.clear()
            textEstre.text.clear()
            textEscar.text.clear()
            textAuto.text.clear()
            textCapa.text.clear()
            textClima.text.clear()
            textRuido.text.clear()

            val action = EditFragmentDirections.actionEditFragmentToFeaturesFragment(idE)
            v.findNavController().navigate(action)
        }
    }
}