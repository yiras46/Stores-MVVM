package com.cursosant.android.stores.editModulo.model

import com.cursosant.android.stores.StoreApplication
import com.cursosant.android.stores.common.entities.StoreEntity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EditInteractor {

    fun getStore(id:Long, callback: (StoreEntity) -> Unit){
        doAsync {
            val storeEntity = StoreApplication.database.storeDao().getStoreById(id)
            uiThread { callback(storeEntity) }
        }
    }
}