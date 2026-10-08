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

        // Passando callbacks para o Adapter
        adapter = ContatoAdapter(
            contatos = emptyList(),
            onDeleteClick = { id ->
                viewModel.deletarContato(id)
            },
            onEditClick = { contato ->
                val intent = Intent(this, CadastroContato::class.java)
                intent.putExtra("contato", contato) // já é Serializable
                startActivity(intent)
            }
        )

        binding.recyclerContatos.layoutManager = LinearLayoutManager(this)
        binding.recyclerContatos.adapter = adapter

        // Observa a lista de contatos
        viewModel.listContato.observe(this) { contatos ->
            adapter.atualizarLista(contatos)

            contatos.forEach {
                Log.d("CONTATO", "Contato: ${it.nome} - (${it.ddd}) ${it.phone}")
            }
        }

        // Botão de adicionar novo contato
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, CadastroContato::class.java)
            startActivity(intent)
        }
    }
}
