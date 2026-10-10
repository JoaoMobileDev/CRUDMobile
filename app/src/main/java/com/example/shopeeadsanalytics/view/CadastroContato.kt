package com.example.shopeeadsanalytics.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.example.shopeeadsanalytics.databinding.ActivityCadastroContatoBinding
import com.example.shopeeadsanalytics.model.Contatos
import com.example.shopeeadsanalytics.viewModel.ListContato

class CadastroContato : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroContatoBinding
    private val viewModel: ListContato by viewModels()

    // -1 significa que NÃO estamos editando
    private var idContatoEdicao: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroContatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tenta pegar o ID enviado pelo Intent
        val contato = IntentCompat.getSerializableExtra(intent, "contato", Contatos::class.java)
        contato?.let {
            idContatoEdicao = it.id
            binding.edtNome.setText(it.nome)
            binding.edtDdd.setText(it.ddd)
            binding.edtTelefone.setText(it.phone)
        }
        binding.btnSalvar.setOnClickListener {
            salvarContato()
        }

        binding.btnCancelar.setOnClickListener {
            finish()
        }
    }

    private fun salvarContato() {

        val nome = binding.edtNome.text.toString().trim()
        val ddd = binding.edtDdd.text.toString().trim()
        val telefone = binding.edtTelefone.text.toString().trim()

        // Limpa erros anteriores
        binding.layoutNome.error = null
        binding.layoutDdd.error = null
        binding.layoutTelefone.error = null

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
        // -------------------------
        // MODO EDIÇÃO
        // -------------------------
        if (idContatoEdicao != -1L) {
            val contatoEditado = Contatos(
                id = idContatoEdicao,
                nome = nome,
                ddd = ddd,
                phone = telefone
            )

            val editou = viewModel.editarContato(contatoEditado)

            if (editou) {
                finish()
            } else {
                binding.layoutTelefone.error =
                    "Este telefone já existe na sua lista de contatos"
            }
        } else {

            // -------------------------
            // NOVO CADASTRO
            // -------------------------

            val novoContato = Contatos(
                id = System.currentTimeMillis(),
                nome = nome,
                ddd = ddd,
                phone = telefone
            )

            val cadastrou = viewModel.novoContato(novoContato)

            if (cadastrou) {

                finish()

            } else {

                binding.layoutTelefone.error =
                    "Este telefone já existe na sua lista de contatos"
            }
        }
    }
}