package com.example.mi_proyecto.navii1

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mi_proyecto.Adapters.ProductoListAdapter
import com.example.mi_proyecto.R
import com.example.mi_proyecto.clases.Entities.Producto
import com.example.mi_proyecto.database.appDatabase
import com.example.mi_proyecto.database.productDao
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_1.*


class Fragment1 : Fragment() {
    lateinit var v: View
    lateinit var recProducto : RecyclerView
    lateinit var btnAdd : FloatingActionButton

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var productoListAdapter: ProductoListAdapter

    private var db : appDatabase? = null
    private var productDao : productDao? = null

    private val PREF_NAME = "myPreferences"
    var user : String = ""
    var posList : Int = 0

    companion object {
        fun newInstance() = Fragment1()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v =  inflater.inflate(R.layout.fragment_1, container, false)
        btnAdd = v.findViewById(R.id.addProductBtn)
        recProducto = v.findViewById(R.id.rec_heladera)
        db = appDatabase.getAppDataBase(v.context)
        productDao = db?.productDao()

//        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
//        var toolbar = v.toolbar2
//        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
//        navHostFragment.navController.addOnDestinationChangedListener { controller, destination, arguments ->
//            toolbar.title = destination.label
//        }

//        var toolbar = requireView().findViewById(R.id.toolbar2) as Toolbar
//        toolbar.inflateMenu(R.menu.menu_toolbar)
//        val menu: Menu = toolbar.menu



//        if(activity is AppCompatActivity){
//            (activity as AppCompatActivity).setSupportActionBar(toolbar2)
//        }
//        (activity as AppCompatActivity).supportActionBar?.title = "Title"

        return v
    }
    //agregado miercoles 9-12 para el toolbar, falta las actions
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
    override fun onStart() {
        super.onStart()


        val producto : MutableList<Producto> = productDao?.loadAllProducts() as MutableList<Producto>
        recProducto.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        recProducto.layoutManager = linearLayoutManager
        productoListAdapter = ProductoListAdapter(
            producto,
            { position -> onItemClick(position) },
            { position ->
                onLongItemClick(
                    position
                )
            })
        recProducto.adapter = productoListAdapter
        btnAdd.setOnClickListener{
//            val sharedPref: SharedPreferences = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//            val editor = sharedPref.edit()
//            editor.putInt("posi", positionToID(position))
//            editor.apply()
            val action = Fragment1Directions.actionFragment1ToNewProdFragment()
           v.findNavController().navigate(action)
        }

    }

    fun onLongItemClick(position: Int): Boolean {
        //Snackbar.make(v,"clickeable", Snackbar.LENGTH_SHORT).show()
        val producto: MutableList<Producto> = productDao?.loadAllProducts() as MutableList<Producto>
        //Snackbar.make(v,position.toString(), Snackbar.LENGTH_SHORT).show()
        val alertDialog: AlertDialog = AlertDialog.Builder(this.context).create()
        //alertDialog.setTitle("ALERTA")
        alertDialog.setMessage("¿Seguro que quiere eliminar el producto: " + producto[position].modelo + "?")
        alertDialog.setButton(
            DialogInterface.BUTTON_POSITIVE,
            "ELIMINAR",
            DialogInterface.OnClickListener { dialog, which ->
                // here you can add functions
                //productDao!!.delete(producto[positionToID(position-1)])
                //val newPosition = productoListAdapter.getItemViewType(position)

                productDao!!.deleteById(positionToID(position))
                recProducto.removeViewAt(position)                          // es necesario?

                productoListAdapter.notifyItemRemoved(position)
                //productoListAdapter.notifyItemRangeChanged(position, 1)
                //productoListAdapter.notifyDataSetChanged()              //va o no va?

                //Log.d("funciona", "el alert dialog")
            })
//        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,"EDITAR", DialogInterface.OnClickListener { dialog, which ->
//            // here you can add functions
//            productDao!!.insertProduct(producto[positionToID(position-1)])
//            val action5 = Fragment1Directions.actionFragment1ToEditFragment()
//            v.findNavController().navigate(action5)
//            Log.d("funciona", "el alert dialog")
//        })
        //alertDialog.setIcon(R.drawable.components)

        alertDialog.show()
        productoListAdapter.notifyDataSetChanged()                  // va o no va?
        //productoListAdapter.notifyItemRemoved(position)
        // falta refrescar la pantalla

        return true
    }
    fun onItemClick(position: Int) {
        //productoListAdapter.notifyDataSetChanged()
        val producto: MutableList<Producto> = productDao?.loadAllProducts() as MutableList<Producto>
        //Snackbar.make(v,positionToID(position).toString(), Snackbar.LENGTH_SHORT).show()
        val action3 = Fragment1Directions.actionFragment1ToFeaturesFragment(positionToID(position))
        v.findNavController().navigate(action3)
//        activity?.findNavController(R.id.nav_host)?.navigate(action3)

//        val action3 = Fragment1Directions.actionFragment1ToFragmentHost(position)
//        val sharedPref: SharedPreferences = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//        val editor = sharedPref.edit()
//        editor.putInt("id", producto[positionToID(position)].id)
//        editor.apply()
//        v.findNavController().navigate(action3)
    }
    fun positionToID(pos: Int) : Int{
        val producto: MutableList<Producto> = productDao?.loadAllProducts() as MutableList<Producto>
        val retValue = producto[pos].id
        return retValue
    }
}