package co.com.geo.waiter.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import co.com.geo.waiter.R
import co.com.geo.waiter.fragment.PlatesFragment
import co.com.geo.waiter.fragment.TableOrderFragment
import co.com.geo.waiter.fragment.TableOrderPagerFragment
import co.com.geo.waiter.fragment.TablesFragment
import co.com.geo.waiter.model.Plate
import co.com.geo.waiter.model.Table
import co.com.geo.waiter.model.Tables
import co.com.geo.waiter.model.waiterName

class TablesActivity : AppCompatActivity(), TablesFragment.OnTableSelectedListener,
                                            TableOrderFragment.OnTableOrderFragmentInteractionListener,
                                            PlatesFragment.OnPlatesFragmentInteractionListener {

    companion object {
        fun intent(context: Context) : Intent {
            val intent = Intent(context, TablesActivity::class.java)
            return intent
        }
    }

    private var is400Land = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)

        is400Land = findViewById<ViewGroup>(R.id.table_order_fragment) != null

        if (findViewById<ViewGroup>(R.id.table_list_fragment) != null) {
            if (supportFragmentManager.findFragmentById(R.id.table_list_fragment) == null) {
                supportFragmentManager.beginTransaction()
                        .add(R.id.table_list_fragment, TablesFragment.newInstance(0))
                        .commit()
            }
        }

        if (is400Land) {
            if (supportFragmentManager.findFragmentById(R.id.table_order_fragment) == null) {
                supportFragmentManager.beginTransaction()
                        .add(R.id.table_order_fragment, TableOrderPagerFragment.newInstance(0))
                        .commit()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.menu_tables_activity, menu)
        return true
    }
    override fun onTableSelected(position: Int?) {
        //TODO: Acá se debe mostrar en el detalle el fragment del pedido correspondiente al plato seleccionado o lanzar la actividad con el pedido
        if (is400Land) {
            val tableOrderFragment = supportFragmentManager.findFragmentById(R.id.table_order_fragment) as TableOrderPagerFragment
            tableOrderFragment.moveToTableOrder(position!!)
        } else {
            startActivity(OrderTableActivity.intent(this, position!!))
        }
    }

    override fun onButtonAddPlatePressed(tableIndex: Int) {
        //TODO: Acá se le informa a la activity que un fragment de los de la derecha pulsó el botón
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

    fun addNewOrder(waiterName: String) {

    }

    override fun onPlateSelected(plate: Plate) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
