package com.cursosant.android.stores.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cursosant.android.stores.common.entities.StoreEntity
import com.cursosant.android.stores.mainModule.model.MainInteractor

class MainViewModel: ViewModel() {

    private var interactor = MainInteractor()

    private val stores = interactor.stores

    fun getStores():LiveData<MutableList<StoreEntity>>{
        return stores
    }

    fun deleteStore(storeEntity: StoreEntity){
        interactor.deleteStore(storeEntity){
            val index = stores.value!!.indexOf(storeEntity)
            if (index != -1){
                stores.value!!.removeAt(index)
            }
        }
    }

    fun updateStore(storeEntity: StoreEntity){
        interactor.updateStore(storeEntity){
            val index = stores.value!!.indexOf(storeEntity)
            if (index != -1){
                stores.value!![index] = storeEntity
                //stores.value = stores.value //Fix refresh
            }
        }
    }

    fun addStore(storeEntity: StoreEntity){
        interactor.addStore(storeEntity){
            storeEntity.apply { id = it }
            stores.value!!.add(storeEntity)
            //stores.value = stores.value //Fix refresh
        }
    }
}