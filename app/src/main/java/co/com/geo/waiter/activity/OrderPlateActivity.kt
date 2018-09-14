package co.com.geo.waiter.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import co.com.geo.waiter.R
import co.com.geo.waiter.model.Plate
import kotlinx.android.synthetic.main.activity_order_plate.*
import kotlinx.android.synthetic.main.content_plate.*

fun makeLongToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

class OrderPlateActivity : AppCompatActivity() {

    companion object {

        public const val EXTRA_PLATE = "extra_plate"
        public const val EXTRA_VARIATION = "extra_variation"

        fun intent(context: Context, plate: Plate) : Intent {
            val intent = Intent(context, OrderPlateActivity::class.java)
            intent.putExtra(EXTRA_PLATE, plate)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_plate)
        myInit()
    }

    private fun myInit() {
        val plate  = intent.getSerializableExtra(EXTRA_PLATE) as Plate
        add_button.setOnClickListener {
            val intent = Intent()
            intent.putExtra(EXTRA_PLATE, plate)
            intent.putExtra(EXTRA_VARIATION, plate_variation.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        val view = LayoutInflater.from(this).inflate(R.layout.content_plate, plate_view , true)
        plate_image.setImageResource(plate.image)
        plate_name.text = plate.name
        plate_price.text = "${plate.price}"
        plate_description.text = plate.description
        Log.i("PLATE", plate.name)
    }
}
