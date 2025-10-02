package edu.ucne.almamaria_ap2_p1.domain.huacales.usecase

import edu.ucne.almamaria_ap2_p1.domain.huacales.model.Huacales
import edu.ucne.almamaria_ap2_p1.domain.huacales.repository.HuacalesRepository
import javax.inject.Inject

class UpsertHuacalesUseCase @Inject constructor(
    private val repository: HuacalesRepository
) {
    suspend operator fun invoke(huacales: Huacales):Result<Int>{
        val nombreResult = validateNombre(huacales.nombreCliente)
        if (!nombreResult.isValid){
            return Result.failure(IllegalArgumentException(nombreResult.error))
        }
        val cantidadResult = validateCantidad((huacales.cantidad.toString()))
        if (!cantidadResult.isValid){
            return Result.failure(IllegalArgumentException(cantidadResult.error))
        }
        val precioResult = validatePrecio((huacales.precio.toString()))
        if (!precioResult.isValid){
            return Result.failure(IllegalArgumentException(precioResult.error))
        }
        return runCatching { repository.upsert(huacales)}
    }
}