package com.example.shopeeadsanalytics.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shopeeadsanalytics.R
import com.example.shopeeadsanalytics.databinding.ActivityCadastroContatoBinding
import com.example.shopeeadsanalytics.databinding.ActivityMainBinding
import com.example.shopeeadsanalytics.model.Contatos
import com.example.shopeeadsanalytics.viewModel.ListContato

class CadastroContato : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroContatoBinding
    private val viewModel: ListContato by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroContatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener {
            salvarContato()
        }
        binding.btnCancelar.setOnClickListener {
            finish()
        }

    }

    private fun salvarContato(){
        val nome=binding.edtNome.text.toString().trim()
        val ddd=binding.edtDdd.text.toString().trim()
        val telefone=binding.edtTelefone.text.toString().trim()

        if (nome.isEmpty()) {
            binding.layoutNome.error = "Informe o nome"
            return
        }
        if (ddd.length != 2) {
            binding.layoutDdd.error = "DDD inválido"
            return
        }

        if (telefone.isEmpty()) {
            binding.layoutTelefone.error = "Informe o telefone"
            return
        }
        val contato = Contatos(
            id = System.currentTimeMillis(),
            nome = nome,
            ddd = ddd,
            phone = telefone
        )
        val cadastrou= viewModel.novoContato(contato)
        if(cadastrou){
            finish()
        }else{
            binding.layoutTelefone.error="Este Telefone já Existe na Sua Lista de Contatos !!!"
        }


    }
}