package edu.ucne.almamaria_ap2_p1.data.huacales.mappers

import edu.ucne.almamaria_ap2_p1.data.huacales.local.HuacalesEntity
import edu.ucne.almamaria_ap2_p1.domain.huacales.model.Huacales

fun HuacalesEntity.toDomain(): Huacales = Huacales(
    idEntrada = IdEntrada,
    Fecha = Fecha,
    NombreCliente = NombreCliente,
    Cantidad = Cantidad,
    Precio = Precio
)

fun Huacales.toEntity(): HuacalesEntity = HuacalesEntity(
    IdEntrada = idEntrada,
    Fecha = Fecha,
    NombreCliente = NombreCliente,
    Cantidad = Cantidad,
    Precio = Precio

)