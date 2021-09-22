package com.cursosant.android.stores.common.utils

import com.cursosant.android.stores.common.entities.StoreEntity


interface MainAux {
    fun hideFab(isVisible: Boolean = false)

    fun addStore(storeEntity: StoreEntity)
    fun updateStore(storeEntity: StoreEntity)
}