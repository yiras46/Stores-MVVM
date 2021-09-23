package com.cursosant.android.stores.mainModule.viewModel

import androidx.lifecycle.*
import com.cursosant.android.stores.common.entities.StoreEntity
import com.cursosant.android.stores.common.utils.StoresExceptions
import com.cursosant.android.stores.common.utils.TypeError
import com.cursosant.android.stores.mainModule.model.MainInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private var interactor = MainInteractor()
    private val _showProgress = MutableLiveData(false)
    private val _typeError:MutableLiveData<TypeError> = MutableLiveData()

    val typeError:LiveData<TypeError> = _typeError
    val showProgress:LiveData<Boolean> = _showProgress
    val stores: LiveData<MutableList<StoreEntity>> = liveData {
        delay(2_000) //FIXME delay temporal de ejemplo
        _showProgress.value = true
        emitSource(interactor.getAllStores())
        _showProgress.value = false
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
            delay(1_000) //FIXME delay temporal
            try {
                block()
            }catch (e:StoresExceptions){
                _typeError.value = e.typeError
            }finally {
                _showProgress.value = false
            }
        }
    }
}