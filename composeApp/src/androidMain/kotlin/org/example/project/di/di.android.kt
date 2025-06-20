package org.example.project.di

import org.example.project.repostory.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun plataformModules(): Module {
    return module {
        single { getDatabaseBuilder(get()) }
    }
}