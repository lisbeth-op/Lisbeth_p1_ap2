package com.example.lisbeth_p1_ap2.data.repository

import com.example.lisbeth_p1_ap2.data.local.dao.DivisionDao
import com.example.lisbeth_p1_ap2.data.local.entities.DivisionEntity
import javax.inject.Inject

class DivisionRepository @Inject constructor(
    private val DivisionDao:DivisionDao
   ){
    suspend fun save(division: DivisionEntity) = DivisionDao.save(division)
    suspend fun delete(Id:Int ) = DivisionDao.delete(Id)
    suspend fun getAll()= DivisionDao.getAll()

    }




