package com.example.pagingapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.pagingapp.R
import com.example.pagingapp.model.UnsplashImageData
import com.example.pagingapp.model.Urls
import com.example.pagingapp.model.User
import com.example.pagingapp.model.UserLinks
import com.example.pagingapp.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()) {

    val images = homeViewModel.images.collectAsLazyPagingItems()

    LazyColumn{
        
    }

}
@Preview(showBackground = true)
@Composable
fun UnSplashImageItems(unsplashImage:UnsplashImageData= UnsplashImageData(id="1234f", Urls(regular = "https://i0.wp.com/picjumbo.com/wp-content/uploads/beautiful-nature-mountain-scenery-with-flowers-free-photo.jpg?w=600&quality=80"), likes = 1234, user = User(
    UserLinks(html = "sample"), username = "SAm"
))) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(unsplashImage.urls.regular).crossfade(true).build(),
        placeholder = painterResource(id = R.drawable.placeholderimage),
        contentScale = ContentScale.Crop,

    )
    Column(modifier = Modifier.fillMaxWidth()){
        Image(
            painter = painter,
            contentDescription = "Image From UnSplash",
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        )
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp).background(color = )){
            Text(text = "Image by ${unsplashImage.user.username}")
        }

    }
    
}