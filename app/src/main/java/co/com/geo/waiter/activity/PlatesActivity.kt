package co.com.geo.waiter.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import co.com.geo.waiter.R
import co.com.geo.waiter.fragment.PlatesFragment
import co.com.geo.waiter.model.Plate

class PlatesActivity : AppCompatActivity(), PlatesFragment.OnPlatesFragmentInteractionListener {

    companion object {
        fun intent(context: Context) : Intent {
            val intent = Intent(context, PlatesActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("PlatesActivity", "onCreate")
        setContentView(R.layout.activity_plates)
    }

    override fun onPlateSelected(plate: Plate) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
