package com.example.authentification

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class LoginActivity2 : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var pass: EditText
    lateinit var btnlog: Button
    lateinit var btnsign: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        name = findViewById(R.id.Name)
        pass = findViewById(R.id.Password)
        btnlog = findViewById(R.id.button)
        btnsign = findViewById(R.id.button4)
        btnsign.setOnClickListener {
            val snack = Snackbar.make(it, "signup", Snackbar.LENGTH_LONG)
            snack.show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val sharedPreference = getSharedPreferences("MonFichier", Context.MODE_PRIVATE)
        // Les informations d'identification n'existent pas encore, donc nous les initialisons ici
        val editor = sharedPreference.edit()
        editor.putString("Adminname", "admin")
        editor.putString("Adminpassword", "111")
        editor.apply()

        btnlog.setOnClickListener {
            val username = name.text.toString()
            val password = pass.text.toString()

            // Récupérer le compteur d'utilisateurs
            val userCount = sharedPreference.getInt("userCount", 0)

            // Vérifier l'authentification de l'utilisateur
            var isUserAuthenticated = false

            for (i in 1..userCount) {
                val registeredName = sharedPreference.getString("name$i", "")
                val registeredPass = sharedPreference.getString("password$i", "")

                if (username == registeredName && password == registeredPass) {
                    isUserAuthenticated = true
                    break
                }
            }

            // Vérifier l'authentification de l'administrateur
            val adminUsername = sharedPreference.getString("Adminname", "")
            val adminPassword = sharedPreference.getString("Adminpassword", "")

            if (isUserAuthenticated) {
                // Authentification réussie pour l'utilisateur
                Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            } else if (username == adminUsername && password == adminPassword) {
                // Authentification réussie pour l'administrateur
                Toast.makeText(this, "Authentification de l'administrateur réussie", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent)
            } else {
                // Authentification échouée
                Toast.makeText(this, "Nom d'utilisateur ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }
}