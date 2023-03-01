package com.example.ctd

import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.clevertap.android.sdk.CleverTapAPI
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    var cleverTapDefaultInstance: CleverTapAPI? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cleverTapDefaultInstance = CleverTapAPI.getDefaultInstance(applicationContext)
        cleverTapDefaultInstance?.pushEvent("DemoGK")
        Log.d("TAG", "onCreate: ")

        CleverTapAPI.createNotificationChannel(applicationContext,"got","Game of Thrones","Game Of Thrones",
            NotificationManager.IMPORTANCE_MAX,true);


        var button = findViewById<Button>(R.id.submit)
        button.setOnClickListener {
            val profileUpdate = HashMap<String, Any>()

            profileUpdate["Name"] = findViewById<EditText>(R.id.name).text.toString()
            profileUpdate["Identity"] = 9142997999 // String or number
            profileUpdate["Email"] = findViewById<EditText>(R.id.email).text.toString() // Email address of the user
            profileUpdate["Phone"] = findViewById<EditText>(R.id.phoneNumber).text.toString() // Phone (with the country code, starting with +)
            profileUpdate["Gender"] = "F" // Can be either M or F
            profileUpdate["DOB"] = Date() // Date of Birth. Set the Date object to the appropriate value first
            profileUpdate.put("Photo", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Cat03.jpg/481px-Cat03.jpg")
            // optional fields. controls whether the user will be sent email, push etc.

            profileUpdate["MSG-email"] = true // Disable email notifications
            profileUpdate["MSG-push"] = true // Enable push notifications
            profileUpdate["MSG-sms"] = true // Disable SMS notifications
            profileUpdate["MSG-whatsapp"] = true // Enable WhatsApp notifications
            val stuff = ArrayList<String>()
            stuff.add("bag")
            stuff.add("shoes")
            profileUpdate["MyStuff"] = stuff //ArrayList of Strings
            val otherStuff = arrayOf("Blue", "Perfume")
            profileUpdate["MyStuff"] = otherStuff //String Array

            CleverTapAPI.getDefaultInstance(applicationContext)?.onUserLogin(profileUpdate)
//            CleverTapAPI.getDefaultInstance(applicationContext)?.pushProfile(profileUpdate)
        }

    }
}