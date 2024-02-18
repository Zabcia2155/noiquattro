package zabski.sebastian.kurs.android.noiquattro.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zabski.sebastian.kurs.android.noiquattro.R
import zabski.sebastian.kurs.android.noiquattro.ui.data.ItemDetail
import zabski.sebastian.kurs.android.noiquattro.ui.data.UiState
import zabski.sebastian.kurs.android.noiquattro.ui.data.samples.sampleItemDetailScreen
import zabski.sebastian.kurs.android.noiquattro.ui.theme.Default50
import zabski.sebastian.kurs.android.noiquattro.ui.theme.Green700
import zabski.sebastian.kurs.android.noiquattro.ui.theme.Green800
import zabski.sebastian.kurs.android.noiquattro.ui.theme.Neutral900

@Composable
fun ProductDetailScreen(
    data: UiState.ItemDetailScreen,
    onItemAdd: (ItemDetail) -> Unit = {},
    onGoToShoppingBag: () -> Unit = {},
) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {

        ProductHeader()
        ProductImage(image = data.item.image)
        ProductDetail(
            item = data.item,
            alreadyAdded = data.alreadyAdded,
            onItemAdd = onItemAdd,
            onGoToShoppingBag = onGoToShoppingBag)
    }

}

@Composable
fun ProductHeader() {

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_left),
            contentDescription = null)
        Icon(modifier = Modifier
            .size(35.dp, 35.dp)
            .clip(CircleShape)
            .background(Neutral900),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_favourite_border),
            contentDescription = null)

    }

}

@Composable
fun ProductImage(image: Int) {

    Surface(modifier = Modifier
        .fillMaxWidth()
        .size(350.dp, 350.dp)) {
        Image(bitmap = ImageBitmap.imageResource(id = image),
            contentDescription = null,
            alignment = Alignment.Center)

    }
}


@Composable
fun ProductHashTag(name:String) {

    Surface(modifier = Modifier.padding(5.dp),
        shadowElevation = 15.dp,
        shape = RoundedCornerShape(10),
        color = Default50) {

        Text(text = name,
            modifier = Modifier.padding(7.dp),
            color = Green700)

    }
}

@Composable
fun ProductDetail(
    item: ItemDetail,
    alreadyAdded: Boolean = false,
    onItemAdd: (ItemDetail) -> Unit = {},
    onGoToShoppingBag: () -> Unit = {}
) {

    var isIngredientsExpanded by rememberSaveable {
    mutableStateOf(false)
    }
    var isCaloriesTableExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    Surface(modifier = Modifier.padding(start = 10.dp, end = 10.dp),
        shape = RoundedCornerShape(10)) {
        
        Column {
            LazyRow{
                items(items = item.hasTags) {tag ->
                    ProductHashTag(name = tag)
                }
            }

            Row(modifier = Modifier.padding(top = 20.dp)) {
                Text(modifier = Modifier.weight(1f),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    text = item.name)
                Text(modifier = Modifier.weight(1f),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    text = item.price.toString(),
                    textAlign = TextAlign.Center)
            }
            
            Column {
                Row(modifier = Modifier
                    .clickable { isIngredientsExpanded = !isIngredientsExpanded }
                    .padding(top = 45.dp)) {

                    val ingredientsArrow = when (isIngredientsExpanded) {
                        true -> ImageVector.vectorResource(id = R.drawable.ic_arrow_down)
                        false -> ImageVector.vectorResource(id = R.drawable.ic_arrow_up)
                    }

                    Text(text = "Składniki", color = Color.Gray)
                    Icon(imageVector = ingredientsArrow, contentDescription = null)
                }

                if (isIngredientsExpanded){
                    Text(modifier = Modifier.padding(15.dp),
                        text = item.ingredients,
                        fontWeight = FontWeight.Bold)
                }
            }
            Column {
                Row(modifier = Modifier
                    .clickable { isCaloriesTableExpanded = !isCaloriesTableExpanded }
                    .padding(top = 45.dp)) {

                    val caloriesArrow = when (isCaloriesTableExpanded) {
                        true -> ImageVector.vectorResource(id = R.drawable.ic_arrow_down)
                        false -> ImageVector.vectorResource(id = R.drawable.ic_arrow_up)
                    }

                    Text(text = "Wartośi odżywcze", color = Color.Gray)
                    Icon(imageVector = caloriesArrow, contentDescription = null)
                }

                    if (isCaloriesTableExpanded) {
                    Text(modifier = Modifier
                        .padding(top = 15.dp),
                        text = item.ingredients,
                        fontWeight = FontWeight.Bold)
                }
            }

            ShoppingBagButton(
                alreadyAdded = alreadyAdded,
                onClick = {onItemAdd(item) },
                onGoToShoppingBag = onGoToShoppingBag)
        }

    }

}

@Composable
fun ShoppingBagButton(
    alreadyAdded: Boolean,
    onClick: () -> Unit = {},
    onGoToShoppingBag: () -> Unit = {},
    ) {

    val defaultModifier = Modifier
        .padding(vertical = 16.dp)
        .height(48.dp)
        .fillMaxWidth()

    when (alreadyAdded) {
        true -> {
            OutlinedButton(
                modifier = Modifier,
                onClick = onGoToShoppingBag,
                colors = ButtonDefaults.buttonColors(Neutral900)
            ) {
                Box(modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart) {

                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start) {

                        Image(
                            modifier = Modifier.size(24.dp, 24.dp),
                            bitmap = ImageBitmap.imageResource(id = R.drawable.ic_already_added),
                            contentDescription = null)
                        Text(
                            modifier = Modifier.padding(start = 10.dp),
                            text = "Dodano",
                            color = Color.White,)

                    }
                }

            }
        }
        false -> {
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(Green800)
            ) {
                
                Text(text = "Dodaj do koszyka", color = Color.White, fontSize = 18.sp)
                
            }
        }

        else -> {}
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProductDetailScreenPreview() {
    ProductDetailScreen(data = sampleItemDetailScreen)
}