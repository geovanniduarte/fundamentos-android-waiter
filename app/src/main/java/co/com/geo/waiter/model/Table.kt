package co.com.geo.waiter.model


data class Table(var number: Int, var roomName: String) {

    var tableOrder: TableOrder? = null

    override fun toString(): String {
         super.toString()
         return  number.toString()
    }
}
