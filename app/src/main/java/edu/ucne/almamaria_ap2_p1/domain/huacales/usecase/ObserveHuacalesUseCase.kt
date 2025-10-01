package edu.ucne.almamaria_ap2_p1.domain.huacales.usecase

import edu.ucne.almamaria_ap2_p1.domain.huacales.model.Huacales
import edu.ucne.almamaria_ap2_p1.domain.huacales.repository.HuacalesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveHuacalesUseCase @Inject constructor(
    val repository: HuacalesRepository
) {
    suspend operator fun invoke(): Flow<List<Huacales>> = repository.ObserveHuacales()
}