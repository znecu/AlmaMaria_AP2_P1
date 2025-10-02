package edu.ucne.almamaria_ap2_p1.domain.huacales.usecase

data class HuacalesValidations (
    val isValid: Boolean,
    val error: String? = null
)

fun validateNombre(value:String): HuacalesValidations{
    if(value.isBlank()) return HuacalesValidations(false, "El nombre es requerido")
    if(value.length < 3) return HuacalesValidations(false, "Minimo 3 caracteres")
    return HuacalesValidations(true)
}

fun validateCantidad(value:String): HuacalesValidations{
    if(value.isBlank()) return HuacalesValidations(false, "La cantidad es requerida")
    val number = value.toIntOrNull()?: return HuacalesValidations(false, "Debe ser un numero entero")
    if(number < 0) return HuacalesValidations(false,"Debe ser mayor o igual a 0")
    return HuacalesValidations(true)
}

fun validatePrecio(value: String): HuacalesValidations{
    if(value.isBlank()) return HuacalesValidations(false, "El precio es requerida")
    val number = value.toDoubleOrNull()?: return HuacalesValidations(false, "Debe ser un numero entero")
    if(number < 0) return HuacalesValidations(false,"Debe ser mayor o igual a 0")
    return HuacalesValidations(true)
}