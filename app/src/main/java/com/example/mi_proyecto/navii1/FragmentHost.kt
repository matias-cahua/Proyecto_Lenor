package com.example.mi_proyecto.navii1

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mi_proyecto.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_host.view.*
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import com.example.mi_proyecto.clases.Entities.Producto
import com.example.mi_proyecto.database.appDatabase
import com.example.mi_proyecto.database.productDao
import kotlinx.android.synthetic.main.fragment_host.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentHost.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentHost : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var producto : Producto
    lateinit var v:View
    lateinit var edtTest : TextView
    private var db : appDatabase? = null
    private var productDao : productDao? = null

    var idH : Int = 0
    private val PREF_NAME = "myPreferences"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment_host, container, false)


        val navHostFragment = childFragmentManager.findFragmentById(R.id.navhost2) as NavHostFragment
        v.bottomBarView.setupWithNavController(navController = navHostFragment.navController)


        val toolbar = v.toolbar
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        navHostFragment.navController.addOnDestinationChangedListener { controller, destination, arguments ->
            toolbar.title = destination.label


        }

        return v

    }

    override fun onStart() {
        super.onStart()
        var posicion  = FragmentHostArgs.fromBundle(requireArguments()).id

        db = appDatabase.getAppDataBase(v.context)
        productDao = db?.productDao()
        producto = productDao?.loadProductById(posicion)!!
        //Snackbar.make(v,producto.marca + " " , Snackbar.LENGTH_SHORT).show()

        val sharedPref: SharedPreferences = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt("posi", posicion)
        editor.apply()


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.navhost2) as NavHostFragment
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                //R.id.fragment0 -> showBothNavigation()
                R.id.profileFragment3 -> menu.findItem(R.id.action_edit).isVisible = false
                R.id.fragment1 -> menu.findItem(R.id.action_edit).isVisible = false
                R.id.featuresFragment -> menu.findItem(R.id.action_setting).isVisible = false
                //R.id.newProdFragment-> menu.findItem(R.id.toolbar).isVisible = false
                //else -> showBothNavigation()
            }
        }
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = when(item.itemId) {

            R.id.action_edit -> ActionEdit()
            //R.id.action_setting -> ActionSetting()

            else -> ""
        }
        return super.onOptionsItemSelected(item)
    }
    private fun ActionEdit(){
        val action = FragmentHostDirections.actionFragmentHostToEditFragment2()
        v.findNavController().navigate(action)
    }
//    private fun ActionSetting(){
//        val action = FragmentHostDirections.actionFragmentHostToSettingsActivity()
//        v.findNavController().navigate(action)
//    }

}
