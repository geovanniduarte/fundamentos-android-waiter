package co.com.geo.waiter.adapter

import android.speech.RecognizerIntent
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import co.com.geo.waiter.R
import co.com.geo.waiter.model.Plate
import co.com.geo.waiter.model.Plates
import kotlinx.android.synthetic.main.content_plate.*

class PlatesReciclerViewAdapter(private val plates: List<Plate>): RecyclerView.Adapter<PlatesReciclerViewAdapter.PlateViewHolder>() {

    var onClickListener : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_plate, parent, false)
        view.setOnClickListener(onClickListener)
        Log.i("PlatesReciclerV", "creatin view holder")
        return PlateViewHolder(view)
    }

    override fun getItemCount(): Int {
        return plates.size
    }


    override fun onBindViewHolder(holder: PlateViewHolder, position: Int) {
        val plate = Plates[position]
        holder.bindPlate(plate)
    }

    inner class PlateViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.plate_image)
        val textPlateName = itemView.findViewById<TextView>(R.id.plate_name)
        val textPlatePrice = itemView.findViewById<TextView>(R.id.plate_price)
        val textPlateDescription = itemView.findViewById<TextView>(R.id.plate_description)

        fun bindPlate(plate: Plate) {
            imageView.setImageResource(plate.image)
            textPlateName.text = plate.name
            textPlatePrice.text = " ${plate.price}"
            textPlateDescription.text = plate.description
        }

    }
}