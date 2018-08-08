package co.com.geo.waiter.activity

import android.app.Activity
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
        val intent = OrderPlateActivity.intent(this, plate)
        startActivityForResult(intent, PLATES_REQUEST)
        makeLongToast(this, "Plato seleccionado")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            PLATES_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {
                        val plate = data.getSerializableExtra(OrderPlateActivity.EXTRA_PLATE)
                        val variation = data.getStringExtra(OrderPlateActivity.EXTRA_VARIATION)
                        val intent = Intent()
                        intent.putExtra(OrderPlateActivity.EXTRA_PLATE, plate)
                        intent.putExtra(OrderPlateActivity.EXTRA_VARIATION, variation)
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    }
                } else {
                    makeLongToast(this, getString(R.string.error_add_plate_toast_msg))
                }
            }
        }
    }
}
