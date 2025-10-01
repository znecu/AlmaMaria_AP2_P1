package edu.ucne.almamaria_ap2_p1.domain.huacales.usecase

import edu.ucne.almamaria_ap2_p1.domain.huacales.repository.HuacalesRepository
import javax.inject.Inject

class GetHuacalesUseCase @Inject constructor(
    val repository: HuacalesRepository
) {
    suspend operator fun invoke(id: Int) = repository.getHuacales(id)
}