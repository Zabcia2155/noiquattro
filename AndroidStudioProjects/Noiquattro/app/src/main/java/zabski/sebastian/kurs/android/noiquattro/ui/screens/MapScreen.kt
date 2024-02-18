package zabski.sebastian.kurs.android.noiquattro.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import zabski.sebastian.kurs.android.noiquattro.R
import zabski.sebastian.kurs.android.noiquattro.ui.data.UiState
import zabski.sebastian.kurs.android.noiquattro.ui.data.samples.sampleMapData
import zabski.sebastian.kurs.android.noiquattro.ui.theme.Default50
import zabski.sebastian.kurs.android.noiquattro.ui.theme.Green800
import zabski.sebastian.kurs.android.noiquattro.ui.theme.Neutral900


@Composable
fun MapScreen(
    data: UiState.Map,
) {

    var isDetailVisible by remember {
        mutableStateOf(true)
    }
    
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                text = "Twoje zamówienie",
                textAlign = TextAlign.Start,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
            OrderMap()
            
        }
        AnimatedVisibility(
            visible = isDetailVisible,
            enter = slideIn { IntOffset(0, 100) } + fadeIn(),
            exit = slideOut { IntOffset(0, 100) } + fadeOut()) {

            Box(contentAlignment = Alignment.BottomCenter) {
                InfoCard(
                    name = data.name,
                    surname = data.surname,
                    souceAddress = data.sourceAddress,
                    targetAddress = data.targetAddress)
            }
        }

        Box(contentAlignment = Alignment.BottomCenter) {
            OutlinedButton(
                colors = ButtonDefaults.buttonColors(Green800),
                shape = RoundedCornerShape(20),
                onClick = { isDetailVisible = !isDetailVisible }) {
                if (isDetailVisible) Text(text = "Ukryj szczegóły", color = Color.White)
                else Text(text = "Zobacz Szczegóły", color = Color.White)

            }
        }
    }
    
}

@Composable
fun InfoCard(
    name: String = "",
    surname: String = "",
    souceAddress: String = "",
    targetAddress: String = "",
) {

    val profilImage = ImageBitmap.imageResource(id = R.drawable.profile_image)

    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.5f),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp), color = Neutral900) {

        Column {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                
                Image(
                    modifier = Modifier
                        .size(60.dp, 60.dp)
                        .padding(end = 16.dp),
                    bitmap = profilImage, contentDescription = null)
                
                Column(horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center) {

                    Text(text = "$name $surname", fontWeight = FontWeight.Bold, color = Color.White)
                    Text(text = "123-345-789", fontWeight = FontWeight.Light, color = Color.White)
                    
                }

                Surface(shape = CircleShape, color = Default50) {
                    IconButton(
                        modifier = Modifier.border(1.dp,
                            color = Color.LightGray,
                            shape = CircleShape),
                        onClick = {  }) {
                        val editIcon = ImageBitmap.imageResource(id = R.drawable.ic_phone)

                        Icon(modifier = Modifier.size(25.dp, 25.dp),
                            bitmap = editIcon,
                            contentDescription = null,
                            tint = Green800)
                        
                    }

                }

            }

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 30.dp)
                    .offset(y = (-20).dp),
                shape = RoundedCornerShape(12),
                shadowElevation = 1.dp) {

                val placeImage = ImageBitmap.imageResource(id = R.drawable.ic_address)
                val clockImage = ImageBitmap.imageResource(id = R.drawable.ic_clock)

                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween) {

                    InfoCardRow(
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                        bitmap = placeImage,
                        address = souceAddress)

                    Image(
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .size(20.dp, 80.dp),
                        bitmap = ImageBitmap.imageResource(id = R.drawable.ic_line),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.LightGray))

                    InfoCardRow(
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
                        bitmap = clockImage,
                        address = targetAddress)
                }

            }

        }
        
    }

}

@Composable
fun InfoCardRow(modifier: Modifier = Modifier, bitmap: ImageBitmap, address: String) {
    Row(modifier = Modifier) {
        IconButton(
            modifier = Modifier.border(1.dp, Color.LightGray, shape = CircleShape),
            onClick = {}) {

            Icon(
                modifier = Modifier.size(25.dp, 25.dp),
                tint = Green800,
                bitmap = bitmap, contentDescription = null)

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = address,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun OrderMap(modifier: Modifier = Modifier) {
    val startPlace = LatLng(50.43692, 16.50272)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(startPlace, 10f)
    }

    Surface(modifier = Modifier.fillMaxSize(), shadowElevation = 1.dp) {
        GoogleMap(modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState) {
            Marker(state = MarkerState(startPlace),
                title = "Wolany 63c",
                snippet = "Marker in Wolany")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MapscreenPreview() {
    MapScreen(data = sampleMapData)
    
}
