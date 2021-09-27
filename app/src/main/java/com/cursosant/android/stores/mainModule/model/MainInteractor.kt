package com.cursosant.android.stores.mainModule.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.cursosant.android.stores.StoreApplication
import com.cursosant.android.stores.common.entities.StoreEntity
import com.cursosant.android.stores.common.utils.Constants
import com.cursosant.android.stores.common.utils.StoresExceptions
import com.cursosant.android.stores.common.utils.TypeError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray

class MainInteractor {

    private val url = Constants.STORES_ENDPOINT + Constants.GET_ALL

    fun getAllStoresApi(callbak:(JSONArray) -> Unit){

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            val status = response.getInt(Constants.STATUS_PROPERTY)
            if(status == Constants.SUCCESS){
                callbak(response.getJSONArray(Constants.STORES_PROPERTY))
            }else{
                throw StoresExceptions(TypeError.API)
            }
        }, {
            it.printStackTrace()
        })

        StoreApplication.storeApi.addToRequestQueue(jsonObjectRequest)
    }

    fun getAllStoresRoom():LiveData<MutableList<StoreEntity>>{
        return StoreApplication.database.storeDao().getAllStores()
            .map { stores -> stores.sortedBy { it.name }.toMutableList() }
    }

    suspend fun addStore(storeEntity: StoreEntity) = withContext(Dispatchers.IO){
        val result = StoreApplication.database.storeDao().addStore(storeEntity)
        if(result == 0L) throw StoresExceptions(TypeError.INSERT)
    }

    suspend fun deleteStore(storeEntity: StoreEntity) = withContext(Dispatchers.IO){
        val result = StoreApplication.database.storeDao().deleteStore(storeEntity)
        if(result == 0) throw StoresExceptions(TypeError.DELETE)
    }

    suspend fun updateStore(storeEntity: StoreEntity) = withContext(Dispatchers.IO){
        val result = StoreApplication.database.storeDao().updateStore(storeEntity)
        if(result == 0) throw StoresExceptions(TypeError.UPDATE)
    }
}