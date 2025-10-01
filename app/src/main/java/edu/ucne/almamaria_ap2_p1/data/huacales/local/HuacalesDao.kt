package edu.ucne.almamaria_ap2_p1.data.huacales.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface HuacalesDao {

    @Query(value = "SELECT * FROM Huacales ORDER BY  IdEntrada DESC")
    fun ObserveAll(): Flow<List<HuacalesEntity>>

    @Query(value= "SELECT * FROM Huacales WHERE IdEntrada = :id" )
    suspend fun getById(id: Int?): HuacalesEntity?

    @Upsert
    suspend fun upsert(entity: HuacalesEntity)

    @Delete
    suspend fun delete(entity: HuacalesEntity)

    @Query(value = "DELETE FROM Huacales WHERE IdEntrada = :id")
    suspend fun deleteById(id: Int)
}