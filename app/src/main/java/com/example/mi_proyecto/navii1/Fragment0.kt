package com.example.mi_proyecto.navii1

//import example.com.mi_proyecto.databinding.0FragmentBinding
//import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mi_proyecto.R
import com.example.mi_proyecto.clases.Entities.User
import com.example.mi_proyecto.database.appDatabase
import com.example.mi_proyecto.database.userDao
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_0.*


class Fragment0 : Fragment() {
    lateinit var v: View
    lateinit var btnLogin: Button
    lateinit var btnRegister: Button
    private val PREF_NAME = "myPreferences"

    private var db: appDatabase? = null
    private var userDao: userDao? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            v = inflater.inflate(R.layout.fragment_0, container, false)
            db = appDatabase.getAppDataBase(v.context)
            userDao = db?.userDao()
            btnLogin = v.findViewById(R.id.btn_login)
            btnRegister = v.findViewById(R.id.btn_register)
            return v
        }
    override fun onStart() {
        super.onStart()
        var users : MutableList<User>
        users = userDao?.loadAllPersons() as MutableList<User>

        btnLogin.setOnClickListener {
            var flagUserOk = false
            var flagPassOk = false
            val username : String = txt_username.text.toString()
            val password : String = txt_password.text.toString()

            lateinit var msjSnackb1 : String
            if (username == "" || password == ""){
                msjSnackb1 = "Campos vacios. Completar"
            }
            else{
                for (item: User in users){
                    if(username == item.username){
                        flagUserOk = true
                        if (password == item.password){
                            flagPassOk = true
                        }
                        break
                    }
                }
                if (flagUserOk){
                    if (flagPassOk){
                        msjSnackb1 = "Login ok"
                        val sharedPref : SharedPreferences = requireContext().getSharedPreferences(
                            PREF_NAME,
                            Context.MODE_PRIVATE
                        )


                        val editor:SharedPreferences.Editor = sharedPref.edit()
                        editor.putString("USER", username)
                        editor.apply()

                        val action = Fragment0Directions.actionFragment0ToFragmentHost(1)
                        v.findNavController().navigate(action)
                    }
                    else{
                        msjSnackb1 = "Password Incorrecto"
                    }
                }
                else{
                    msjSnackb1 = "Usuario no existe"
                }
            }
            val snackBar = Snackbar.make(it, msjSnackb1, Snackbar.LENGTH_LONG).setAction(
                "action",
                null
            )
            snackBar.setActionTextColor(Color.WHITE)
            val snackBarView = snackBar.view
            snackBarView.setBackgroundColor(Color.BLACK)
            val textView =
                snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            textView.setTextColor(Color.BLUE)
            snackBar.show()
        }
        btnRegister.setOnClickListener {
            val action2 = Fragment0Directions.actionFragment0ToFragmentRegister()
            v.findNavController().navigate(action2)
        }
    }


}