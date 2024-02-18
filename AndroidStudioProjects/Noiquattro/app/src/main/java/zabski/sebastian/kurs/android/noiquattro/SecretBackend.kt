package zabski.sebastian.kurs.android.noiquattro

import kotlinx.coroutines.delay
import kotlinx.coroutines.selects.SelectInstance
import zabski.sebastian.kurs.android.noiquattro.ui.data.ItemDetail
import zabski.sebastian.kurs.android.noiquattro.ui.data.Order
import zabski.sebastian.kurs.android.noiquattro.ui.data.UserData
import zabski.sebastian.kurs.android.noiquattro.ui.data.samples.sampleItemDetailOne
import zabski.sebastian.kurs.android.noiquattro.ui.data.samples.sampleItemDetailTwo
import zabski.sebastian.kurs.android.noiquattro.ui.data.samples.sampleItemDetailZero
import zabski.sebastian.kurs.android.noiquattro.ui.data.samples.sampleUserOne
import zabski.sebastian.kurs.android.noiquattro.ui.data.samples.sampleUserTwo

class SecretBackend {

    private val user = listOf(sampleUserOne, sampleUserTwo)
    private val items = listOf(sampleItemDetailZero, sampleItemDetailOne, sampleItemDetailTwo)
    private val history = arrayListOf<Order>()

    suspend fun login(email: String, password: String): UserData? {
        delay(2000)
        return user.find {it.email == email && it.password == password }
    }

    fun getItemDetail(id: Long): ItemDetail? {
        return items.find { it.id == id }
    }

    fun getAllItems(): List<ItemDetail> {
        return items
    }

    fun saveOrderInHistory(orders: Set<Order>) {
        history.addAll(orders)
    }

    fun getOrdersHistory() = history.toList()

}
