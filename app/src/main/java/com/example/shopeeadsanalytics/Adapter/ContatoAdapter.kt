package com.example.shopeeadsanalytics.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopeeadsanalytics.databinding.ItemContatoBinding
import com.example.shopeeadsanalytics.model.Contatos

class ContatoAdapter(
    private var contatos: List<Contatos>,
    private val onDeleteClick: (Long) -> Unit,
    private val onEditClick: (Contatos) -> Unit
) : RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder>() {

    class ContatoViewHolder(val binding: ItemContatoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        val binding = ItemContatoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContatoViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
        val contato = contatos[position]

        holder.binding.tvNome.text = contato.nome
        holder.binding.tvTelefone.text = "(${contato.ddd}) ${contato.phone}"

        holder.binding.btnExcluirMain.setOnClickListener {
            onDeleteClick(contato.id)
        }

        holder.binding.btnEditarMain.setOnClickListener {
            onEditClick(contato)
        }
    }

    override fun getItemCount(): Int = contatos.size

    @SuppressLint("NotifyDataSetChanged")
    fun atualizarLista(novaLista: List<Contatos>) {
        contatos = novaLista
        notifyDataSetChanged()
    }
}