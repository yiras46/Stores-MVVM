package com.cursosant.android.stores.editModulo.model

import com.cursosant.android.stores.StoreApplication
import com.cursosant.android.stores.common.entities.StoreEntity

class EditInteractor {

    suspend fun getStore(id:Long):StoreEntity {
        return StoreApplication.database.storeDao().getStoreById(id)
    }
}