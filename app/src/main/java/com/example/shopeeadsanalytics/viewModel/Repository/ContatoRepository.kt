package com.example.shopeeadsanalytics.viewModel.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopeeadsanalytics.model.Contatos

object ContatoRepository {

    private val _listaContato= MutableLiveData<List<Contatos>>(emptyList())

    val listContato: LiveData<List<Contatos>>
        get()=_listaContato


    fun novoContato(contato: Contatos): Boolean{
        val listaAtual = _listaContato.value.orEmpty().toMutableList()
        val numeroExiste=listaAtual.any{
            it.ddd==contato.ddd && it.phone==contato.phone
        }
        if (numeroExiste){
            Log.d(
                "CONTATO",
                "Esse Telefone já está cadastrado: ${contato.nome} - (${contato.ddd}) ${contato.phone}"
            )
            return false
        }
        listaAtual.add(contato)
        _listaContato.value=listaAtual
        return true
    }
    fun deletarContato(contatoId: Long){
        val listaAtual = _listaContato.value.orEmpty().toMutableList()
        listaAtual.removeAll{
            it.id==contatoId
        }
        _listaContato.value=listaAtual
    }
    fun editarContato(contatoAtualizado: Contatos): Boolean {
        val listaAtual = _listaContato.value.orEmpty().toMutableList()

        val index = listaAtual.indexOfFirst {
            it.id == contatoAtualizado.id
        }

        // Contato não encontrado na lista
        if (index == -1) {
            Log.d("CONTATO", "Contato não encontrado para edição: id ${contatoAtualizado.id}")
            return false
        }
        // Verifica se o telefone já existe em OUTRO contato (ignora o próprio)
        val numeroExiste = listaAtual.any {
            it.id != contatoAtualizado.id &&
                    it.ddd == contatoAtualizado.ddd &&
                    it.phone == contatoAtualizado.phone
        }
        if (numeroExiste) {
            Log.d(
                "CONTATO",
                "Esse Telefone já está cadastrado: ${contatoAtualizado.nome} - (${contatoAtualizado.ddd}) ${contatoAtualizado.phone}"
            )
            return false
        }
        listaAtual[index] = contatoAtualizado
        _listaContato.value = listaAtual
        return true
    }
}