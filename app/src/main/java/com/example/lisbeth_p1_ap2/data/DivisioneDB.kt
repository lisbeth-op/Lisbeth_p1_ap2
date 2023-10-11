package com.example.lisbeth_p1_ap2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lisbeth_p1_ap2.data.local.dao.DivisionDao
import com.example.lisbeth_p1_ap2.data.local.entities.DivisionEntity


@Database(
    entities = [DivisionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DivisioneDB : RoomDatabase() {
    abstract fun DivisionDao(): DivisionDao

}