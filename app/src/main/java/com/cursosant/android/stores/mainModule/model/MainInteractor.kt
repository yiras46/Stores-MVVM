package com.cursosant.android.stores.mainModule.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.cursosant.android.stores.StoreApplication
import com.cursosant.android.stores.common.entities.StoreEntity
import com.cursosant.android.stores.common.utils.StoresExceptions
import com.cursosant.android.stores.common.utils.TypeError

class MainInteractor {

    fun getAllStores():LiveData<MutableList<StoreEntity>>{
        return StoreApplication.database.storeDao().getAllStores()
            .map { stores -> stores.sortedBy { it.name }.toMutableList() }
    }

    suspend fun addStore(storeEntity: StoreEntity){
        StoreApplication.database.storeDao().addStore(storeEntity)
    }

    suspend fun deleteStore(storeEntity: StoreEntity){
        val result = StoreApplication.database.storeDao().deleteStore(storeEntity)
        if(result == 0) throw StoresExceptions(TypeError.DELETE)
    }

    suspend fun updateStore(storeEntity: StoreEntity){
        val result = StoreApplication.database.storeDao().updateStore(storeEntity)
        if(result == 0) throw StoresExceptions(TypeError.UPDATE)
    }
}