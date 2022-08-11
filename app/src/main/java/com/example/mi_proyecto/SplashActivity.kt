package com.example.mi_proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import com.example.mi_proyecto.clases.Entities.Producto
import com.example.mi_proyecto.clases.Entities.User
import com.example.mi_proyecto.database.appDatabase
import com.example.mi_proyecto.database.productDao
import com.example.mi_proyecto.database.userDao

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 3000 // 3 sec
//    lateinit var tv:TextView
//    lateinit var iv: ImageView
    private var db: appDatabase? = null
    private var userDao: userDao? = null
    private var productDao: productDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        db = appDatabase.getAppDataBase(this)
        userDao = db?.userDao()
        productDao = db?.productDao()
//        tv = this.findViewById(R.id.splash_text)
//        iv = this.findViewById(R.id.image_lenor)
//        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition)
//        tv.startAnimation(myanim)
//        iv.startAnimation(myanim)
        var users : MutableList<User>
        users = userDao?.loadAllPersons() as MutableList<User>
        if(users.isEmpty()){
            userDao?.insertPerson(User(0, "m","matias@matias.com","1"))

            productDao?.insertProduct(
            Producto(1,"SAMSUNG","RT46K6635S8","Refrigerador con Congelador","A++",250,
                155,55,6,"****","Sin Escarcha",15,3.5,"T",39, "https://i.imgur.com/TUc7Ino.png?1" )
        )
            productDao?.insertProduct(
            Producto(2,"MIDEA","RF-T16SAR1","Refrigerador con Congelador","A+",280,
                180,60,0,"****","Sin Escarcha",17,4.0,"ST",41,"https://i.imgur.com/M2sBD4w.png?1" )
        )
            productDao?.insertProduct(
            Producto(3,"Whirlpool","WRM45AB","Refrigerador con Congelador","A+",267,
                173,77,7,"****","Sin Escarcha",16,4.5,"SN-T",37, "https://i.imgur.com/qmBG9RQ.png?2" )
        )
            productDao?.insertProduct(
            Producto(4,"PEABODY","PE-FV90IX","Congelador Vertical","A",290,
                0,149,20,"****","Sin Escarcha",21,8.0,"T",44, "https://i.imgur.com/gLyB8wp.png?1" )
        )
            productDao?.insertProduct(
            Producto(5,"FRARE","FR170","Congelador tipo arcón","B",380,
                0,172,0,"****","No",29,14.0,"N-T",40, "https://i.imgur.com/cg2QUJO.png?1" )
        )
            productDao?.insertProduct(
            Producto(6,"LACAR","R10WW01","Refrigerador","B",180,
                55,0,0,"-","No",0,0.0,"SN",37, "https://i.imgur.com/WQ25QxV.png?1" )
            )
        }

        Handler().postDelayed(

            {
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            , SPLASH_TIME_OUT)
    }
}