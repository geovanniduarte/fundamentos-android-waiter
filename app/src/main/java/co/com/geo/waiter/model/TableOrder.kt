package co.com.geo.waiter.model

import java.io.Serializable

class TableOrder(var table: Table, var waiterName: String, var date: Long): Serializable {
    val orders : ArrayList<Order> = ArrayList()

    fun getPlates() : List<Plate> {
        val plates = ArrayList<Plate>()
        for (order:Order in this.orders) {
            plates.add(order.plate)
        }
        return plates
    }

    fun addOrder(order: Order) {
        orders.add(order)
    }

}