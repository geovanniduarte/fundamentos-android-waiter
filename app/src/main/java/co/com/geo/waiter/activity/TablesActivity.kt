package co.com.geo.waiter.activity

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import co.com.geo.waiter.R
import co.com.geo.waiter.fragment.OrderFragment
import co.com.geo.waiter.fragment.TablesFragment
import co.com.geo.waiter.model.Plate
import co.com.geo.waiter.model.Table

class TablesActivity : AppCompatActivity(), TablesFragment.OnTableSelectedListener, OrderFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)

        if (findViewById<ViewGroup>(R.id.table_list_fragment) != null) {
            if (supportFragmentManager.findFragmentById(R.id.table_list_fragment) == null) {
                supportFragmentManager.beginTransaction()
                        .add(R.id.table_list_fragment, TablesFragment.newInstance(0))
                        .commit()
            }
        }

        if (findViewById<ViewGroup>(R.id.table_detail_fragment) != null) {
            if (supportFragmentManager.findFragmentById(R.id.table_detail_fragment) == null) {
                supportFragmentManager.beginTransaction()
                        .add(R.id.table_list_fragment, OrderFragment.newInstance(0))
                        .commit()
            }
        }
    }

    override fun onTableSelected(item: Table?) {
        //TODO: Acá se debe mostrar en el detalle el fragment del pedido correspondiente al plato seleccionado o lanzar la actividad con el pedido
    }

    override fun onNewPlateAdded(plate: Plate) {
        //TODO: Acá se le informa al fragment con los platos pedidos que debe agregar un nuevo plato
    }
}
