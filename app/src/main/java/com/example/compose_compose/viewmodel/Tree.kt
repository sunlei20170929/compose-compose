package com.example.compose_compose.viewmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trees")
data class Tree (
    @PrimaryKey @ColumnInfo(name = "id") val treeid: String,
    val name:String
){
    override fun toString() = name
}