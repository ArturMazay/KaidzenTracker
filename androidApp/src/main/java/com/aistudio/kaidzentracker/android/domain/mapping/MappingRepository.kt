package com.aistudio.kaidzentracker.android.domain.mappinginterface MappingRepository {    suspend fun get(): Mapping?    suspend fun save(mapping: Mapping)    suspend fun delete()}