package edu.ucne.almamaria_ap2_p1.domain.huacales.usecase

import edu.ucne.almamaria_ap2_p1.domain.huacales.repository.HuacalesRepository
import javax.inject.Inject

class DeleteHuacalesUseCase @Inject constructor(
    private val repository: HuacalesRepository
) {
    suspend operator fun invoke(id: Int) = repository.deleteById(id)
}