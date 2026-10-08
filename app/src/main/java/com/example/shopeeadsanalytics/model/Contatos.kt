package com.example.shopeeadsanalytics.model

import java.io.Serializable


data class Contatos(
    val id: Long,
    val nome:String,
    val ddd: String,
    val phone: String
): Serializable