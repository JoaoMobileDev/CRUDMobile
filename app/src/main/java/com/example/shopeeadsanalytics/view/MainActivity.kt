package com.example.shopeeadsanalytics.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopeeadsanalytics.Adapter.ContatoAdapter
import com.example.shopeeadsanalytics.databinding.ActivityMainBinding
import com.example.shopeeadsanalytics.viewModel.ListContato

class MainActivity : AppCompatActivity() {

    private val viewModel: ListContato by viewModels()

    private lateinit var adapter: ContatoAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ContatoAdapter(emptyList())

        binding.recyclerContatos.layoutManager =
            LinearLayoutManager(this)

        binding.recyclerContatos.adapter = adapter


        viewModel.listContato.observe(this) { contatos ->

            adapter.atualizarLista(contatos)

            contatos.forEach {
                Log.d(
                    "CONTATO",
                    "Contato: ${it.nome} - (${it.ddd}) ${it.phone}"
                )
            }
        }


        binding.btnAdd.setOnClickListener {

            val intent = Intent(
                this,
                CadastroContato::class.java
            )

            startActivity(intent)
        }
    }
}