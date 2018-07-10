package co.com.geo.waiter.model

object Tables {
    private val tables : List<Table> = listOf(
            Table(1, "One"),
            Table(2, "Two"),
            Table(3, "Three"),
            Table(4, "Four"),
            Table(5, "Five"),
            Table(6, "Six"),
            Table(7, "Seven"),
            Table(8, "Eight"),
            Table(9, "Nine"),
            Table(10, "Ten")
    )

    fun toArray() = tables.toTypedArray()

    fun count() = tables.count()

    operator fun get(index: Int) = tables[index]
}
