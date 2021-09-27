package com.cursosant.android.stores.mainModule.viewModel

import androidx.lifecycle.*
import com.cursosant.android.stores.common.entities.StoreEntity
import com.cursosant.android.stores.common.utils.StoresExceptions
import com.cursosant.android.stores.common.utils.TypeError
import com.cursosant.android.stores.mainModule.model.MainInteractor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private var interactor = MainInteractor()
    private val _showProgress = MutableLiveData(false)
    private val _typeError:MutableLiveData<TypeError> = MutableLiveData()

    val typeError:LiveData<TypeError> = _typeError
    val showProgress:LiveData<Boolean> = _showProgress
    val stores: LiveData<MutableList<StoreEntity>> = liveData {
        _showProgress.value = true
        try {
            getStoresApi()
        }catch (e:StoresExceptions){
            _typeError.value = e.typeError
        }finally {
            _showProgress.value = false
            emitSource(interactor.getAllStoresRoom())
        }
    }

    fun deleteStore(storeEntity: StoreEntity){
        executeAction { interactor.deleteStore(storeEntity) }
    }

    fun updateStore(storeEntity: StoreEntity){
        executeAction { interactor.updateStore(storeEntity) }
    }

    fun addStore(storeEntity: StoreEntity){
        executeAction { interactor.addStore(storeEntity) }
    }

    private fun executeAction(block: suspend() -> Unit ):Job{

        return viewModelScope.launch {
            _showProgress.value = true
            try {
                block()
            }catch (e:StoresExceptions){
                _typeError.value = e.typeError
            }finally {
                _showProgress.value = false
            }
        }
    }

    private fun getStoresApi(){
        viewModelScope.launch {
            interactor.getAllStoresApi { stores ->
                stores.forEach {
                    addStore(it)
                }
            }
        }
    }
}