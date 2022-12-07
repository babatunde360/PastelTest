package com.babatunde.pasteltest.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.babatunde.pasteltest.model.Article
    @Composable
    fun HomeScreen(localMovieList: List<Article>) {
        val context = LocalContext.current
        Scaffold(modifier = Modifier.fillMaxSize()) {
            LazyColumn(content = {

                itemsIndexed(
                    localMovieList){index,article ->
                    if (index==0){
                        Box(modifier = Modifier
                            .fillMaxWidth()) {
                            Text(text = "Top news",
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
                    }else {
                        ImageCard(article = article, context = context)
                    }
                }

            })
        }
    }
