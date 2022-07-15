package space.mosk.therickandmorty

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load

class CharacterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        val arguments = intent.extras
        val name = arguments!!["name"].toString()
        val image = arguments!!["image"].toString()
        val status = arguments!!["status"].toString()
        val gender = arguments!!["gender"].toString()
        val species = arguments!!["species"].toString()
        val type = arguments!!["type"].toString()
        val origin = arguments!!["origin"].toString()
        val location = arguments!!["location"].toString()

        val img_user = findViewById<ImageView>(R.id.img_icon)
        img_user.load(image)

        findViewById<TextView>(R.id.text_name).text = name
        findViewById<TextView>(R.id.text_status).text = status
        findViewById<TextView>(R.id.text_gender).text = gender
        findViewById<TextView>(R.id.text_species).text = species
        findViewById<TextView>(R.id.text_type).text = type
        findViewById<TextView>(R.id.text_origin).text = origin
        findViewById<TextView>(R.id.text_location).text = location


        val btn_back = findViewById<Button>(R.id.btn_back)
        btn_back.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(0, 0)
        })
    }
}