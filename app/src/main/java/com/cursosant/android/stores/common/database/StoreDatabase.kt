package com.cursosant.android.stores.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cursosant.android.stores.common.entities.StoreEntity


@Database(entities = [StoreEntity::class], version = 2)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun storeDao(): StoreDao
}