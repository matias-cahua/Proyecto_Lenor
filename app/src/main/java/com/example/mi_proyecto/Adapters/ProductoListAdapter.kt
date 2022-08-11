package com.example.mi_proyecto.Adapters

//import com.bumptech.glide.Glide
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mi_proyecto.R
import com.example.mi_proyecto.clases.Entities.Producto
import java.util.*

class ProductoListAdapter(
    private var productosList: MutableList<Producto>,
    val onItemClick: (Int) -> Unit,
    val onLongItemClick: (Int) -> Boolean
) : RecyclerView.Adapter<ProductoListAdapter.ProductHolder>() {

    companion object {

        private val TAG = "ProductoListAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder { //va a buscar el layout del item_producto
        val view =  LayoutInflater.from(parent.context).inflate(
            R.layout.item_producto,
            parent,
            false
        )


        return (ProductHolder(view))
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) { //mezcla los datos de la lista y el holder. Se llama tantas veces como items tiene la lista
        holder.setName(productosList[position].modelo)
        holder.setImg((productosList[position].urlImage))
        holder.getCardLayout().setOnClickListener {
            onItemClick(position)
        }
        holder.getCardLayout().setOnLongClickListener {
            onLongItemClick(position)
        }

    }

    override fun getItemCount(): Int {   //cantidad de elementos de salida, o sea de mi lista que se obtiene de la lista
        return productosList.size
    }

    class ProductHolder(v: View) : RecyclerView.ViewHolder(v) {    //tiene los metodos para modificar el item_producto

        private var view: View

        init {
            this.view = v
        }

        fun setName(name: String) {
            val txt: TextView = view.findViewById(R.id.txt_name_item)
            txt.text = name
        }
        fun setImg(url: String){
            val img : ImageView = view.findViewById(R.id.img_item)
            Glide
                .with(img.context)
                .load(url)
                .apply(RequestOptions().override(200, 600))
                .into(img)
        }
        fun getCardLayout(): CardView {
            return view.findViewById(R.id.card_package_item)
        }

        fun getImageView () : ImageView {
            return view.findViewById(R.id.img_item)
        }
    }


}