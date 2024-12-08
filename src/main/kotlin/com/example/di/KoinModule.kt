package com.example.di

import com.example.repository.HeroRepository
import com.example.repository.HeroRepositoryImp
import org.koin.dsl.module

/**
 * Koin module definition for dependency injection.
 *
 * This module specifies the dependency injection configuration
 * using Koin framework. It declares a single instance binding for
 * the HeroRepository interface, providing an implementation
 * via HeroRepositoryImp.
 *
 * HeroRepository is an interface with methods for retrieving
 * hero data, searching heroes by name, and calculating pagination details.
 * HeroRepositoryImp is the concrete implementation providing
 * the functionality for these operations.
 */
val koinModule = module{
    single<HeroRepository> {
        HeroRepositoryImp()
    }
}