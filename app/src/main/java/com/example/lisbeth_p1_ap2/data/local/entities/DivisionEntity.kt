package com.example.lisbeth_p1_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Divisiones")
class DivisionEntity (
    @PrimaryKey
    val DivisionId: Int?= null,
    var Nombres:String="",
    var Dividendo:Int,
    var Divisor:Int,
    var Cociente: Int,
    var Residuo: Int,
)
