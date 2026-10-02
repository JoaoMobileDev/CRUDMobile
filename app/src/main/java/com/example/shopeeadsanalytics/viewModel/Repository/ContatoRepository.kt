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
    fun editarContato(contatoAtualizado: Contatos){
        val listaAtual = _listaContato.value.orEmpty().toMutableList()
        val index=listaAtual.indexOfFirst {
            it.id==contatoAtualizado.id
        }
        if (index != -1) {

            listaAtual[index] = contatoAtualizado

            _listaContato.value = listaAtual
        }
    }
}