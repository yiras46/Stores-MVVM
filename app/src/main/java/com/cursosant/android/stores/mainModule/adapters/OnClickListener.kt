package com.cursosant.android.stores.mainModule.adapters

import com.cursosant.android.stores.common.entities.StoreEntity

interface OnClickListener {
    fun onClick(storeId: Long)
    fun onFavoriteStore(storeEntity: StoreEntity)
    fun onDeleteStore(storeEntity: StoreEntity)
}