package edu.ucne.almamaria_ap2_p1.domain.huacales.repository

import edu.ucne.almamaria_ap2_p1.data.huacales.local.HuacalesEntity
import edu.ucne.almamaria_ap2_p1.domain.huacales.model.Huacales
import kotlinx.coroutines.flow.Flow

interface HuacalesRepository {

    fun ObserveHuacales(): Flow<List<Huacales>>

    suspend fun getHuacales(id: Int?): Huacales?

    suspend fun upsert(huacales: Huacales): Int

    suspend fun delete(huacales: Huacales)

    suspend fun deleteById(id: Int)
}