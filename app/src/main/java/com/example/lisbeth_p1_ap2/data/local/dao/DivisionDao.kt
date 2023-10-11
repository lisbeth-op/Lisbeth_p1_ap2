package com.example.lisbeth_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lisbeth_p1_ap2.data.local.entities.DivisionEntity

@Dao
interface DivisionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(divisionEntity: DivisionEntity)

    @Query(
        """
        SELECT * 
        FROM divisiones 
        WHERE DivisionId=:id  
        LIMIT 1
        """
    )
    suspend fun find(id: Int): DivisionEntity?


}