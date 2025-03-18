package example.getpassengers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GetPassengers : AppCompatActivity() {
    var passList: MutableList<Passenger> = ArrayList()
    private lateinit var textFirst: EditText
    private lateinit var textLast: EditText
    private lateinit var textPhone: EditText
    private lateinit var textPut: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_passengers)
        textFirst = findViewById(R.id.first_name)
        textLast = findViewById(R.id.last_name)
        textPhone = findViewById(R.id.phone_number)
        textPut = findViewById(R.id.accum_list)
    }

    fun enterPassenger(v: View) {
        val fName = textFirst.text.toString().trim()
        val lName = textLast.text.toString().trim()
        val phone = textPhone.text.toString().trim()
        if (fName.isEmpty()) {
            textFirst.error = "First name required"
            return
        }
        if (lName.isEmpty()) {
            textLast.error = "Last name required"
            return
        }
        if (phone.isEmpty()) {
            textPhone.error = "Phone number required"
            return
        }
        val newPass = Passenger(fName, lName, phone)
        passList.add(newPass)
        textPut.append("\n" + newPass.toString())
        textFirst.text.clear()
        textLast.text.clear()
        textPhone.text.clear()
        textFirst.requestFocus()
    }

    fun backToMain(v: View) {
        val resultIntent = Intent()
        val count = passList.size
        resultIntent.putExtra("COUNT", count.toString())
        for (i in passList.indices) {
            val key = "PASS${i + 1}"
            resultIntent.putExtra(key, passList[i].toString())
        }
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}
