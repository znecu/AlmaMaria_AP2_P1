package edu.ucne.almamaria_ap2_p1.data.huacales.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.type.DateTime


@Entity(tableName = "Huacales")
class HuacalesEntity(
    @PrimaryKey(autoGenerate = true)
    val IdEntrada: Int = 0,
    val Fecha: String = "",
    val NombreCliente: String = "",
    val Cantidad: Int,
    val Precio: Double
)


