package com.example.lisbeth_p1_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Divisiones")

class DivisionEntity (
    @PrimaryKey
    val dividirID: Int?= null,
    var nombre:String="",
    var dividendo:Int,
    var divisor:Int,
    var cociente: Int,
    var residuo: Int,
)
