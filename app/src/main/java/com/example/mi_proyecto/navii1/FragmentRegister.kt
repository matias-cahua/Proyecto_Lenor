package com.example.mi_proyecto.navii1

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.mi_proyecto.R
import com.example.mi_proyecto.clases.Entities.User
import com.example.mi_proyecto.database.appDatabase
import com.example.mi_proyecto.database.userDao
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_register.*


class FragmentRegister : Fragment() {
    lateinit var v : View

    private var db: appDatabase? = null
    private var userDao: userDao? = null

    lateinit var edtUsuario : EditText
    lateinit var edtEmail : EditText
    lateinit var edtPassword1 : EditText
    lateinit var edtPassword2 : EditText

    lateinit var btnRegister2 : Button
    lateinit var userList :MutableList<User>

    var i : Int =0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_register, container, false)

        edtUsuario = v.findViewById(R.id.txt_usuario)
        edtEmail = v.findViewById(R.id.txt_mail)
        edtPassword1 = v.findViewById(R.id.txt_password1)
        edtPassword2 = v.findViewById(R.id.txt_password2)
        btnRegister2 = v.findViewById(R.id.btn_register2)
        return v
    }

    override fun onStart() {
        super.onStart()

        db = appDatabase.getAppDataBase(v.context)
        userDao = db?.userDao()

        btnRegister2.setOnClickListener {
            val username : String = txt_usuario.text.toString()
            val password1 : String = txt_password1.text.toString()
            val password2 : String = txt_password2.text.toString()
            lateinit var msjSnackb2: String


            if (password1 == password2){
                userDao?.insertPerson(User(i, edtUsuario.text.toString(), edtEmail.text.toString(), edtPassword1.text.toString()))
                i += 1
                msjSnackb2 = "Registro exitoso! Inicie sesion."
                val action3 = FragmentRegisterDirections.actionFragmentRegisterToFragment0()
                v.findNavController().navigate(action3)
            }
            else{
                msjSnackb2 = "Contraseñas no coinciden! Vuelva a intentarlo."
            }

            val snackBar = Snackbar.make(it, msjSnackb2, Snackbar.LENGTH_LONG).setAction(
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
    }
}