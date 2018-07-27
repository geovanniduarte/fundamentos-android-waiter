package co.com.geo.waiter.model

import co.com.geo.waiter.R

object Plates {
    private val plates : List<Plate> = listOf(
            Plate("Sancocho", 15000, "description 1", R.drawable.bandeja_150),
            Plate("Bandeja paisa", 18000, "description 2", R.drawable.bandeja_150)
    )

    operator fun get(index: Int) = plates[index]

    fun getPlates() = plates

}