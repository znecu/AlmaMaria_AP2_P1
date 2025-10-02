package edu.ucne.almamaria_ap2_p1.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.almamaria_ap2_p1.data.huacales.local.HuacalesEntity
import edu.ucne.almamaria_ap2_p1.domain.huacales.repository.HuacalesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HuacalesRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHucalaesRepository(
        hucalesRepositoryImpl: HuacalesRepository
    ): HuacalesEntity

}