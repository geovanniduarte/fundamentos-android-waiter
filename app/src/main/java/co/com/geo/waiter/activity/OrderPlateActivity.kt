package co.com.geo.waiter.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.com.geo.waiter.R
import co.com.geo.waiter.model.Plate

class OrderPlateActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_PLATE = "extra_plate"

        fun intent(context: Context, plate: Plate) : Intent {
            val intent = Intent(context, OrderTableActivity::class.java)
            intent.putExtra(EXTRA_PLATE, plate)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_plate)

    }
}
