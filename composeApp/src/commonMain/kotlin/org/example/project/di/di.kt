package org.example.project.di

import org.example.project.repostory.database.getRoomDatabase
import org.koin.core.module.Module
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.module

expect fun plataformModules() : Module

val commonModules = module {

    single { getRoomDatabase(get()) }

}

fun getKoinConfig(): KoinConfiguration {
    return KoinConfiguration{
        modules(plataformModules(), commonModules)
    }
}