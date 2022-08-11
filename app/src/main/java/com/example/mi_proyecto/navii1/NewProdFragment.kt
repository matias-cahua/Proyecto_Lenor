package com.example.mi_proyecto.navii1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.mi_proyecto.R
import com.example.mi_proyecto.clases.Entities.Producto
import com.example.mi_proyecto.database.appDatabase
import com.example.mi_proyecto.database.productDao
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_new_prod.*
import kotlin.random.Random


class NewProdFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var v: View
    lateinit var btnSave: Button

    private var db: appDatabase? = null
    private var productDAO: productDao? = null
    var positionList : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_new_prod, container, false)
        db = appDatabase.getAppDataBase(v.context)
        productDAO = db?.productDao()
        btnSave = v.findViewById(R.id.btn_save)
        return v
    }

    override fun onStart() {
        super.onStart()
        btnSave.setOnClickListener{
            val productos: MutableList<Producto> = productDAO?.loadAllProducts() as MutableList<Producto>

            var product = Producto(0,textMarca.text.toString(), textModelo.text.toString(),textCategoria.text.toString(),
                textClase.text.toString(),textConsumo.text.toString().toInt(),textVolRefri.text.toString().toInt(),
                textVolCong.text.toString().toInt(),textVolCong2.text.toString().toInt(),textEstre.text.toString(),textEscar.text.toString(),
                textAuto.text.toString().toInt(),textCapa.text.toString().toDouble(),textClima.text.toString(),50,
                "https://i.imgur.com/iPtl3Y3.png" )

            product.id = productos.last().id + (56..465545).random()

            productDAO?.insertProduct(product)

            Snackbar.make(v, "Se agrego " + textMarca.text, Snackbar.LENGTH_SHORT).show()

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
            //textRuido.text.clear()

            val action = NewProdFragmentDirections.actionNewProdFragmentToFragment1()
            v.findNavController().navigate(action)

        }
    }


}