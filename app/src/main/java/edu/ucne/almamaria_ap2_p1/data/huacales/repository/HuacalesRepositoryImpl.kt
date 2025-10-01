package edu.ucne.almamaria_ap2_p1.data.huacales.repository

import edu.ucne.almamaria_ap2_p1.data.huacales.local.HuacalesDao
import edu.ucne.almamaria_ap2_p1.data.huacales.mappers.toDomain
import edu.ucne.almamaria_ap2_p1.data.huacales.mappers.toEntity
import edu.ucne.almamaria_ap2_p1.domain.huacales.model.Huacales
import edu.ucne.almamaria_ap2_p1.domain.huacales.repository.HuacalesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HuacalesRepositoryImpl @Inject constructor(
    private val dao: HuacalesDao
): HuacalesRepository {
    override fun ObserveHuacales(): Flow<List<Huacales>> = dao.ObserveAll().map { list ->
        list.map { it.toDomain() }
    }

    override suspend fun getHuacales(id: Int?): Huacales? = dao.getById(id)?.toDomain()

    override suspend fun upsert(huacales: Huacales): Int {
        dao.upsert(huacales.toEntity())
        return huacales.idEntrada
    }

    override suspend fun delete(huacales: Huacales) {
        dao.delete(huacales.toEntity())
    }

    override suspend fun deleteById(id: Int) {
        dao.deleteById(id)
    }
}