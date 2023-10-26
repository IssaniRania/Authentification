package com.example.authentification

import android.content.Context
import android.content.SharedPreferences
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var Editcin:EditText
    lateinit var Editemail:EditText
    lateinit var Editname: EditText
    lateinit var Editpassword:EditText
    lateinit var btnsave:Button;
    lateinit var btnlogin:Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnlogin=findViewById(R.id.button3)
        btnsave=findViewById(R.id.button2)
        Editcin=findViewById(R.id.editCin)
        Editemail=findViewById(R.id.editEmail)
        Editname=findViewById(R.id.editName)
        Editpassword=findViewById(R.id.editPassword)
        btnlogin.setOnClickListener{
            val snack = Snackbar.make(it,"login", Snackbar.LENGTH_LONG)
            snack.show()
            val intent = Intent(this,LoginActivity2::class.java)
            startActivity(intent)
        }
       // val sharedPreference = SharedPreferences(this)
        val sharedPreference = getSharedPreferences("MonFichier", Context.MODE_PRIVATE)
        btnsave.setOnClickListener {
            val name = Editname.editableText.toString()
            val email = Editemail.text.toString()
            val cin = Editcin.editableText.toString()
            val psd = Editpassword.editableText.toString()

            val editor = sharedPreference.edit()

            val userCount = sharedPreference.getInt("userCount", 0)
            val newUserCount = userCount + 1

            editor.putString("cin$userCount", cin)
            editor.putString("name$userCount", name)
            editor.putString("email$userCount", email)
            editor.putString("password$userCount", psd)
            editor.putInt("userCount", newUserCount)

            editor.apply()

            Toast.makeText(this@MainActivity, "Enregistrement effectué", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity2::class.java)
            startActivity(intent)
        }
        //admin
      //  val sharedPreferences = getSharedPreferences("AdminPreferences", Context.MODE_PRIVATE)
//        val Adminusername = sharedPreferences.getString("username", null)
//        val Adminpassword = sharedPreferences.getString("password", null)
//
//        if (Adminusername == null || Adminpassword == null) {
            // Les informations d'identification n'existent pas encore, donc nous les initialisons ici
//        val editor = sharedPreferences.edit()
//        editor.putString("username", "admin")
//        editor.putString("password", "admin111")
//        editor.apply()
        //}
    }
}