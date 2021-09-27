package com.cursosant.android.stores.common.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cursosant.android.stores.common.entities.StoreEntity


@Dao
interface StoreDao {
    @Query("SELECT * FROM StoreEntity")
    fun getAllStores() : LiveData<MutableList<StoreEntity>>

    @Query("SELECT * FROM StoreEntity where id = :id")
    suspend fun getStoreById(id: Long): StoreEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStore(storeEntity: StoreEntity): Long

    @Update
    suspend fun updateStore(storeEntity: StoreEntity):Int

    @Delete
    suspend fun deleteStore(storeEntity: StoreEntity):Int
}