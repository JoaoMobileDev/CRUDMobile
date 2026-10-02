package com.example.shopeeadsanalytics.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shopeeadsanalytics.view.CadastroContato
import com.example.shopeeadsanalytics.R
import com.example.shopeeadsanalytics.databinding.ActivityMainBinding
import com.example.shopeeadsanalytics.viewModel.ListContato

class MainActivity : AppCompatActivity() {

    private val viewModel: ListContato by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, CadastroContato::class.java)
            startActivity(intent)
        }
        viewModel.listContato.observe(this) { contatos ->

            contatos.forEach {
                Log.d(
                    "CONTATO",
                    "Contato: ${it.nome} - (${it.ddd}) ${it.phone}"
                )
            }


        }
    }
}