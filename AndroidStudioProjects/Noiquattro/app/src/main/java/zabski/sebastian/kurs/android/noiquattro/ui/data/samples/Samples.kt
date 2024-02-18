package zabski.sebastian.kurs.android.noiquattro.ui.data.samples


import zabski.sebastian.kurs.android.noiquattro.R
import zabski.sebastian.kurs.android.noiquattro.ui.data.ItemDetail
import zabski.sebastian.kurs.android.noiquattro.ui.data.Order
import zabski.sebastian.kurs.android.noiquattro.ui.data.UiState
import zabski.sebastian.kurs.android.noiquattro.ui.data.UserData

val sampleUserOne =
    UserData(1L, "Jan", "Kowalski", "Krakowiaków 13a", email = "1@wp.pl", password = "1234")
val sampleUserTwo = UserData(2L, "Kuba", "Nowak", "Wola 13a", email = "2@wp.pl", password = "1234")

val sampleItemDetailZero = ItemDetail(
    id = 0L,
    orderState = "Dostarczone",
    name = "Trzy sery",
    date = "5 Czerwca 2022",
    ingredients = "Mozarella, gran padano, gouda",
    price = 100.99f,
    image = R.drawable.pizza_two
)

val sampleItemDetailOne = ItemDetail(
    id = 1L,
    orderState = "Dostarczone",
    name = "Ser i pomidory",
    date = "5 Czerwca 2022",
    ingredients = "Ser i pomidor, podwójne pepperoni, meksykańska x2",
    price = 91.23f,
    image = R.drawable.pizza_one
)

val sampleItemDetailTwo = ItemDetail(
    id = 2L,
    orderState = "Dostarczone",
    name = "Pepperoni z kiełbasą ",
    date = "5 Czerwca 2022",
    ingredients = "Ser i pomidor, podwójne pepperoni, meksykańska x2",
    image = R.drawable.pizza_two,
    price = 35.99f
)

val sampleOrdersOne = buildList {
    for (i in 0..10) {
        val order = Order(item = sampleItemDetailOne.copy(id = i.toLong(), price = i.toFloat()), i)
        add(order)
    }
}.toList()

val sampleOrdersTwo = buildList {
    for (i in 0..10) {
        add(sampleItemDetailTwo.copy(id = i.toLong()))
    }
}.toList()

val sampleHeader = listOf("Pizza", "Sałatki", "Wegańskie", "Desery")

val sampleHomeData = UiState.Home(
    products = sampleOrdersTwo,
    userData = sampleUserOne
)

val sampleOrderHistoryData =
    UiState.OrderHistory(orderList = sampleOrdersOne)

val sampleEmptyOrderHistoryData =
    UiState.OrderHistory(orderList = emptyList())


val sampleItemDetailScreen =
    UiState.ItemDetailScreen(item = sampleOrdersOne[0].item)

val sampleProfile = UiState.Profile(
    name = "Jan",
    surname = "Kowalski",
    sourceAddress = "Krakowiaków 13a",
    targetAddress = "Głodomorska 12"
)

val sampleShoppingBag = UiState.ShoppingBag(itemList = sampleOrdersOne)

val samplePayment = UiState.Payment(userData = sampleUserOne, orderList = sampleOrdersOne)

val sampleMapData = UiState.Map(
    name = "Kuba",
    surname = "Kowalski",
    sourceAddress = "Krakowiaków 13a",
    targetAddress = "Głodomorska 12"
)


