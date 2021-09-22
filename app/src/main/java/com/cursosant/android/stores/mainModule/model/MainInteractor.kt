package com.cursosant.android.stores.mainModule.model

import com.cursosant.android.stores.StoreApplication
import com.cursosant.android.stores.common.entities.StoreEntity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainInteractor {

    fun getStores(callback: (MutableList<StoreEntity>) -> Unit){
        doAsync {
            val storeList = StoreApplication.database.storeDao().getAllStores()
            uiThread {
                callback(storeList)
            }
        }
    }

    fun addStore(storeEntity: StoreEntity, callback: (Long) -> Unit){

        doAsync {
            val id = StoreApplication.database.storeDao().addStore(storeEntity)
            uiThread {
                callback(id)
            }
        }
    }

    fun deleteStore(storeEntity: StoreEntity, callback: (StoreEntity) -> Unit){

        doAsync {
            StoreApplication.database.storeDao().deleteStore(storeEntity)
            uiThread {
                callback(storeEntity)
            }
        }
    }

    fun updateStore(storeEntity: StoreEntity, callback: (StoreEntity) -> Unit){
        doAsync {
            StoreApplication.database.storeDao().updateStore(storeEntity)
            uiThread {
                callback(storeEntity)
            }
        }
    }
}