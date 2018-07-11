package co.com.geo.waiter.model


class Table(var number: Int, var roomName: String) {

    override fun toString(): String {
         super.toString()
         return  number.toString()
    }
}
