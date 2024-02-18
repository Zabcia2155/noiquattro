package zabski.sebastian.kurs.android.noiquattro.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import zabski.sebastian.kurs.android.noiquattro.R
import zabski.sebastian.kurs.android.noiquattro.ui.data.ItemDetail
import zabski.sebastian.kurs.android.noiquattro.ui.data.UiState
import zabski.sebastian.kurs.android.noiquattro.ui.data.samples.sampleHeader
import zabski.sebastian.kurs.android.noiquattro.ui.data.samples.sampleHomeData
import zabski.sebastian.kurs.android.noiquattro.ui.theme.Green800
import zabski.sebastian.kurs.android.noiquattro.ui.theme.Neutral900

@Composable
fun HomeScreen(
    data: UiState.Home,
    onItemClick: (ItemDetail) -> Unit,
    onProfileClick: () -> Unit,
    onSearch: (String) -> Unit,
    it: String,
) {

    val scrollState = rememberScrollState()
    var text by remember {
        mutableStateOf("")
    }
    var selectedCategoryTab by rememberSaveable {
        mutableStateOf("Pizza")
    }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(start = 2.dp, end = 2.dp, bottom = 10.dp)) {

        HomeHeader(
            address = data.userData.address,
            onProfileClick = onProfileClick)
        WelcomeText(name = data.userData.name)
        SearchField(text = text, onSearch = {
            text = it
            onSearch(it)
        })
        PromotionAds()
        OfferList(
            headers = sampleHeader,
            products = data.products,
            selectedCategoryTab = selectedCategoryTab,
            onTabClick = { category ->
                selectedCategoryTab = category
            },
            onItemClick = onItemClick
        )
    }
}

@Composable
fun OfferList(
    headers: List<String> = emptyList(),
    selectedCategoryTab: String = "",
    products: List<ItemDetail> = emptyList(),
    onTabClick: (String) -> Unit = {},
    onItemClick: (ItemDetail) -> Unit = {},
) {
    Column() {
        TabHeaders(
            selectedTab = selectedCategoryTab,
            headers = headers,
            onTabClick = onTabClick
        )

        LazyRow {
            items(items = products){item ->
                val bitmap = ImageBitmap.imageResource(id = item.image)
                OfferItem(bitmap = bitmap,
                    item = item,
                    onItemClick = onItemClick
                )
            }
        }
    }
}

@Composable
fun OfferItem(
    bitmap: ImageBitmap,
    item: ItemDetail,
    onItemClick: (ItemDetail) -> Unit = {},
){

    Surface(modifier = Modifier
        .padding(horizontal = 5.dp)
        .clickable { onItemClick(item) },
        shape = RoundedCornerShape(10)) {

        Column {
            Row {
                Image(
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                        .offset(x = 25.dp),
                    bitmap = bitmap,
                    contentDescription = null,
                    alignment = Alignment.TopCenter)

                Surface(shape = RoundedCornerShape(10)){
                    Box(contentAlignment = Alignment.TopCenter){
                        Image(modifier = Modifier.size(50.dp, 50.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                            contentDescription = null,
                            alignment = Alignment.BottomEnd,
                            colorFilter = ColorFilter.tint(Green800))
                    }
                }
            }
            Text(modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                text = item.name,
                fontWeight = FontWeight.Bold)
            Text(modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 16.dp),
                text = item.price.toString(),)
        }

    }

}

@Composable
fun TabHeaders(
    selectedTab: String = "Pizza",
    headers: List<String> = emptyList(),
    onTabClick: (String) -> Unit = {},
) {

    LazyRow {
        items(items = headers) { header ->
            Text(modifier = Modifier
                .clickable { onTabClick(header) }
                .padding(horizontal = 16.dp, vertical = 10.dp),
                text = header,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = if (selectedTab == header) Color.Black else Color.Gray)
        }
    }

}

@Composable
fun PromotionAds() {
    Surface(color = Neutral900,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp),
        shape = RoundedCornerShape(10)) {

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(start = 10.dp, top = 16.dp)) {
                Text(text = "20% zniżki",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(text = "Pizza wegetariańska",
                    color = Color.White
                )
                IconButton(modifier = Modifier
                    .padding(5.dp)
                    .border(
                        border = BorderStroke(1.dp, Color.LightGray),
                        shape = RoundedCornerShape(10)
                    ),
                    onClick = {  }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_right),
                        contentDescription = null,
                        tint = Color.White)

                }
            }
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd) {

                Image(modifier = Modifier.size(150.dp, 150.dp),
                    bitmap = ImageBitmap.imageResource(id = R.drawable.pizza_three),
                    contentDescription = null)
            }
        }

    }

}

@Composable
fun SearchField(
    text: String,
    onSearch: () -> Unit,
) {

    OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 10.dp), value = text, onValueChange = { onSearch(it) }, label = { Text(text = "Wyszukaj dane") }, leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Neutral900
        ),
        shape = RoundedCornerShape(20)
    )

}

fun onSearch(it: String) {

}

@Composable
fun WelcomeText(name: String = "") {
    Column(Modifier.padding(top = 20.dp)) {
        Text(modifier = Modifier.padding(start = 5.dp),
            text = "Cześć $name, \nna co masz ochotę?",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold)

    }

}

@Composable
fun HomeHeader(
    address: String = "",
    onProfileClick: () -> Unit = {},
) {

    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    
    val arrowIcon = when (isExpanded) {
        true -> ImageVector.vectorResource(id = R.drawable.ic_arrow_down)
        false -> ImageVector.vectorResource(id = R.drawable.ic_arrow_up)
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 5.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.Center) {

        Column {
            Row (modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(text = "Twój adres", color = Color.Gray)
                Icon(imageVector = arrowIcon, contentDescription = null, tint = Green800)
            }

            if (isExpanded) {
                Text(text = address,
                    fontWeight = FontWeight.Bold)
            }
        }
        
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd) {

            Image(modifier = Modifier
                .clickable { onProfileClick() }
                .size(48.dp, 48.dp)
                .clip(CircleShape),
                bitmap = ImageBitmap.imageResource(id = R.drawable.profile_image),
                contentDescription = null)
        }

    }

}

@Preview(showBackground = true)
@Composable
fun HomeHeaderPreview() {
    HomeHeader(address = "Krakowiaków 13a")
    
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        data = sampleHomeData,
        onItemClick = { /*TODO*/ },
        onProfileClick = { /*TODO*/ }) {
    }
}

fun HomeScreen(
    data: UiState.Home,
    onItemClick: (ItemDetail) -> Unit,
    onProfileClick: () -> Unit,
    it: () -> Unit) = Unit
