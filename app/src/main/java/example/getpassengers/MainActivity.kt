// Brandon Hornick

package example.getpassengers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private lateinit var listText: TextView


    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val countStr = data?.getStringExtra("COUNT") ?: "0"
            val count = countStr.toInt()

            listText.text = "RETURNED PASSENGER LIST:\n"

            for (i in 1..count) {
                val passKey = "PASS$i"
                val passString = data?.getStringExtra(passKey) ?: ""
                listText.append("$passString\n")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        listText = findViewById(R.id.show_list)
    }


    fun getList(v: View) {



        val intent = Intent(this, GetPassengers::class.java)
        startForResult.launch(intent)
    }
}
