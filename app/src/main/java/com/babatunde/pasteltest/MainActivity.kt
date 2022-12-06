package com.babatunde.pasteltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.babatunde.pasteltest.ui.screens.HomeScreen
import com.babatunde.pasteltest.ui.theme.PastelTestTheme
import com.babatunde.pasteltest.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val newsViewModel: MainViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PastelTestTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxHeight(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(topBar = {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.background)) {
                            Text(text = "Newsfeed",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                            modifier = Modifier.padding(16.dp))
                        }
                    },
                        content = {
                        val localMovieList = newsViewModel.
                        newsList.
                        collectAsState(initial = emptyList()).value
                        HomeScreen(localMovieList)
                    })
                }
            }
        }
    }
}
    @Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PastelTestTheme {
        //Greeting("Android")
    }
}