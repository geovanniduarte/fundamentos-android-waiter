package co.com.geo.waiter.activity

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import co.com.geo.waiter.R
import co.com.geo.waiter.fragment.PlatesFragment
import co.com.geo.waiter.fragment.TableOrderFragment
import co.com.geo.waiter.fragment.TableOrderPagerFragment
import co.com.geo.waiter.fragment.TablesFragment
import co.com.geo.waiter.model.Plate
import co.com.geo.waiter.model.Table

class TablesActivity : AppCompatActivity(), TablesFragment.OnTableSelectedListener,
                                            TableOrderFragment.OnFragmentInteractionListener,
                                            PlatesFragment.OnFragmentInteractionListener {


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
        //TODO: Acá se le informa al fragment con los platos pedidos que debe agregar un nuevo plato
    }

    override fun onPlateSelected(plate: Plate) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
