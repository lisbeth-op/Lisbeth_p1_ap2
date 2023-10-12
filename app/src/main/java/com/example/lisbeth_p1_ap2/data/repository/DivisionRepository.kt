package com.example.lisbeth_p1_ap2.data.repository

import com.example.lisbeth_p1_ap2.data.local.dao.DivisionDao
import javax.inject.Inject

class DivisionRepository @Inject constructor(
    private val DivisionDao:DivisionDao
   ){
    suspend fun save(division:DivisionDao) = DivisionDao.save(division)
    suspend fun delete(division:DivisionDao) = DivisionDao.delete(division)
    fun getAll()= DivisionDao.getAll()

    }




