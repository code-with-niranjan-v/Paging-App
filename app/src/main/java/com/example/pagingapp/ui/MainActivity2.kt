package com.example.pagingapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pagingapp.R
import com.example.pagingapp.ui.data.remote.UserApi
import com.example.pagingapp.ui.model.User
import com.example.pagingapp.ui.ui.theme.PagingAppTheme
import com.example.pagingapp.ui.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity2 : ComponentActivity() {

    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PagingAppTheme {
                 Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val users = viewModel.pageSource.collectAsLazyPagingItems()
                     UserScreen(userPages = users)
                }
            }
        }

        
    }
}

@Composable
fun UserScreen(userPages:LazyPagingItems<User>) {
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(userPages){userData->
            userData?.let { UserItems(userData = it) }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun UserItems(
    userData:User = User("123","hellp","lmk","kmk","https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.peakpx.com%2Fen%2Fsearch%3Fq%3Dprofile%2Bpic&psig=AOvVaw2EREtseVAZubcSCpeXCmgc&ust=1710261369748000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqFwoTCMDHzp3S7IQDFQAAAAAdAAAAABAE")
) {

    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ){
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(userData.picture)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholderimage),
            contentScale = ContentScale.Crop,
            contentDescription = "User Image",
            modifier = Modifier
                .clip(CircleShape)
                .height(48.dp)
                .width(48.dp)

        )

        Text(text = userData.firstName, fontSize = 18.sp,modifier = Modifier.padding(horizontal = 12.dp))
    }

}
