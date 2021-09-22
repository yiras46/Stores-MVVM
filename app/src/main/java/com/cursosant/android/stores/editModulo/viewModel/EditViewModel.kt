package com.cursosant.android.stores.editModulo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cursosant.android.stores.common.entities.StoreEntity
import com.cursosant.android.stores.editModulo.model.EditInteractor

class EditViewModel:ViewModel() {

    private var interactor = EditInteractor()

    private val editStore: MutableLiveData<StoreEntity> by lazy {
        MutableLiveData<StoreEntity>()
    }

    fun getEditStore(): LiveData<StoreEntity> {
        return editStore
    }

    fun loadStore(id: Long) {
        if(id <= 0){
            editStore.value = StoreEntity(name = "", phone = "", photoUrl = "")
        }else {
            interactor.getStore(id) {
                editStore.value = it
            }
        }
    }
}