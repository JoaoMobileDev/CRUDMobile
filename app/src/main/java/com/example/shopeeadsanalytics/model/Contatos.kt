package com.example.shopeeadsanalytics.model


import androidx.room3.Entity
import androidx.room3.PrimaryKey
import java.io.Serializable


@Entity(tableName="contatos")
data class Contatos(
    @PrimaryKey(autoGenerate=true)
    val id: Long,
    val nome:String,
    val ddd: String,
    val phone: String
): Serializable