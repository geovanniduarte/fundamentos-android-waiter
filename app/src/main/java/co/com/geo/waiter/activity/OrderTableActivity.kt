package co.com.geo.waiter.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import co.com.geo.waiter.R
import co.com.geo.waiter.adapter.PlatesReciclerViewAdapter
import co.com.geo.waiter.fragment.PlatesFragment
import co.com.geo.waiter.fragment.TableOrderFragment
import co.com.geo.waiter.model.*
import java.util.*

const val PLATES_REQUEST = 1
class OrderTableActivity : AppCompatActivity() , TableOrderFragment.OnTableOrderFragmentInteractionListener, PlatesFragment.OnPlatesFragmentInteractionListener {

    private var tableIndex: Int? = null

    companion object {
        private const val ARG_CITY_INDEX = "ARG_CITY_INDEX"


        fun intent(context: Context, cityIndex: Int) : Intent {
            val intent = Intent(context, OrderTableActivity::class.java)
            intent.putExtra(ARG_CITY_INDEX, cityIndex)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_table)
        tableIndex = intent.getIntExtra(ARG_CITY_INDEX,0)
        if (supportFragmentManager.findFragmentById(R.id.table_order_fragment) == null) {
            val fragment = TableOrderFragment.newInstance(tableIndex!!)
            supportFragmentManager.beginTransaction()
                    .add(R.id.table_order_fragment, fragment)
                    .commit()
        }
    }

    override fun onButtonAddPlatePressed(tableIndex: Int) {
        val tableOrder = Tables[tableIndex].tableOrder
        if (tableOrder == null) {
            val customView = layoutInflater.inflate(R.layout.view_neworder, null)
            val spinnerWaiter = customView.findViewById<Spinner>(R.id.waiter_spinner)
            val values = waiterName.values().map { waiterName ->
                waiterName.name
            }
            val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, values)
            spinnerWaiter.adapter = adapter

            AlertDialog.Builder(this)
                    .setTitle(getString(R.string.new_order))
                    .setView(customView)
                    .setPositiveButton(android.R.string.ok, { param1, param2 ->
                        spinnerWaiter.selectedItem.let {
                            val variable = it
                            addNewOrder(variable.toString())
                        }
                    })
                    .show()
        } else {
            startActivityForResult(PlatesActivity.intent(this), PLATES_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            PLATES_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    val variation = data!!.getStringExtra(OrderPlateActivity.EXTRA_VARIATION)
                    val plate = data!!.getSerializableExtra(OrderPlateActivity.EXTRA_PLATE) as Plate
                    val table = Tables[tableIndex!!]
                    table.tableOrder!!.addOrder(Order(plate, variation))
                    val fragment = supportFragmentManager.findFragmentById(R.id.table_order_fragment) as TableOrderFragment
                    fragment.updateView(table, null)
                }
            }
        }
    }

    fun addNewOrder(waiterName: String) {
        val table = Tables[tableIndex!!]
        val fragment = supportFragmentManager.findFragmentById(R.id.table_order_fragment) as TableOrderFragment

        val tableOrder = TableOrder(table, waiterName, Date().time)
        table.tableOrder = tableOrder

        // TODO: Actualizar el fragment PlatesFragment diciendo que ya hay una orden creada.
        fragment.updateView(null, waiterName)
    }

    override fun onPlateSelected(plate: Plate) {
        //para cuando se seleccione un plato pero del fragment con platos de la orden
    }

}
