package com.example.shopeeadsanalytics.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopeeadsanalytics.model.Contatos
import com.example.shopeeadsanalytics.viewModel.Repository.ContatoRepository

class ListContato : ViewModel(){

    val listContato: LiveData<List<Contatos>>
        get() = ContatoRepository.listContato

    fun novoContato(contato: Contatos): Boolean{
        return ContatoRepository.novoContato(contato)
    }
    fun editarContato(contato: Contatos){
        ContatoRepository.editarContato(contato)
    }
    fun deletarContato(conatoId:Long){
        ContatoRepository.deletarContato(conatoId)
    }
}