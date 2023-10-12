package com.example.lisbeth_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.lisbeth_p1_ap2.data.local.entities.DivisionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DivisionDao {
    @Upsert
    suspend fun save(divisionEntity: DivisionDao)

    @Query(
        """
        SELECT * 
        FROM Divisiones 
        WHERE dividirID=:id  
        LIMIT 1
        """
    )
    suspend fun find(id: Int): DivisionEntity?

    @Delete
    suspend fun delete(divisionEntity: DivisionDao)

    @Query("SELECT * FROM Divisiones")
    fun getAll(): Flow<List<DivisionEntity>>
}
