package zabski.sebastian.kurs.android.noiquattro.ui.data

sealed class UiState {
    data class Home(
        val products: List<ItemDetail>,
        val userData: zabski.sebastian.kurs.android.noiquattro.ui.data.UserData,
    )

    data class Profil(
        val name: String,
        val surname: String,
        val email: String,
    )

    data class ShoppingBag(val itemList: List<Order>)

    data class OrderHistory(val orderList: List<Order>)

    data class ItemDetailScreen(
        val item: ItemDetail,
        val alreadyAdded: Boolean = false,
    )

    data class Payment(
        val userData: zabski.sebastian.kurs.android.noiquattro.ui.data.UserData,
        val orderList: List<Order>,
    )

    data class Map(
        val name: String,
        val surname: String,
        val sourceAddress: String,
        val targetAddress: String,
    )

    class Profile(
        val name: String,
        val surname: String,
        val email: String,
        val sourceAddress: String,
        val targetAddress: String,
    )
}
