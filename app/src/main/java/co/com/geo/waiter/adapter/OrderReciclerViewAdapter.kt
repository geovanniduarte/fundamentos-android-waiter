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
import co.com.geo.waiter.model.Order
import co.com.geo.waiter.model.Plate
import co.com.geo.waiter.model.Plates
import kotlinx.android.synthetic.main.content_plate.*

class OrderReciclerViewAdapter(private val orders: List<Order>): RecyclerView.Adapter<OrderReciclerViewAdapter.OrderViewHolder>(), Listenable {

    override var onClickListener : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_plate, parent, false)
        view.setOnClickListener(onClickListener)

        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.bindOrder(order)
        holder.itemView.tag = order
    }

    inner class OrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val imageView = itemView.findViewById<ImageView>(R.id.plate_image)
        val textPlateName = itemView.findViewById<TextView>(R.id.plate_name)
        val textPlatePrice = itemView.findViewById<TextView>(R.id.plate_price)
        val textPlateDescription = itemView.findViewById<TextView>(R.id.plate_description)

        fun bindOrder(order: Order) {
            imageView.setImageResource(order.plate.image)
            textPlateName.text = order.plate.name
            textPlatePrice.text = " ${order.plate.price}"
            textPlateDescription.text = order.plate.description
        }

    }
}