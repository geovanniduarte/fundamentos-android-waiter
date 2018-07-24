package co.com.geo.waiter.activity

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
import co.com.geo.waiter.fragment.PlatesFragment
import co.com.geo.waiter.fragment.TableOrderFragment
import co.com.geo.waiter.model.Plate
import co.com.geo.waiter.model.TableOrder
import co.com.geo.waiter.model.Tables
import co.com.geo.waiter.model.waiterName
import java.util.*

class OrderTableActivity : AppCompatActivity() , TableOrderFragment.OnFragmentInteractionListener, PlatesFragment.OnFragmentInteractionListener {

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
    }

    fun addNewOrder(waiterName: String) {
        val table = Tables[tableIndex!!]
        val tableOrder = TableOrder(table, waiterName, Date().time)
        table.tableOrder = tableOrder
        // TODO: Actualizar el fragment PlatesFragment diciendo que ya hay una orden creada.
    }

    override fun onPlateSelected(plate: Plate) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
