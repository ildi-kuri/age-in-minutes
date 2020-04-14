package hu.innobyte.android.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        btnDatePicker.setOnClickListener { view ->
            clickDataPicker(view)
        }
    }

    fun clickDataPicker(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val selectedDate = "$year ${monthOfYear + 1} $dayOfMonth"

                    tvSelectedDate.text = selectedDate

                    val sdf = SimpleDateFormat("yyyy MM dd", Locale.ENGLISH)
                    val theDate = sdf.parse(selectedDate)
                    val selectedDateToMinutes = theDate!!.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateToMinutes = currentDate!!.time / 60000
                    val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes
                    tvSelectedDateInMinutes.text = differenceInMinutes.toString()
                },
                year,
                month,
                day
        )

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}
