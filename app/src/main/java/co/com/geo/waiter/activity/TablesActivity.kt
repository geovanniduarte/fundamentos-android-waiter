package co.com.geo.waiter.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import co.com.geo.waiter.R
import co.com.geo.waiter.fragment.TablesFragment
import co.com.geo.waiter.model.Table

class TablesActivity : AppCompatActivity(), TablesFragment.OnTableSelectedListener {

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
    }

    override fun onTableSelected(item: Table?) {

    }
}
