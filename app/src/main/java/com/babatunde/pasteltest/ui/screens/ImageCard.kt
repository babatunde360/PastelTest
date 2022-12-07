package com.babatunde.pasteltest.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.babatunde.pasteltest.model.Article
import com.babatunde.pasteltest.R
import com.babatunde.pasteltest.utils.Utils.openTab

@Composable
fun ImageCard(
    article: Article,
    modifier: Modifier = Modifier,
    context: Context
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                openTab(context, article.url)
            },
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.height(200.dp)
        ) {
            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(context)
                    .data(data = article.urlToImage)
                    .error(R.drawable.no_image_placeholder)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(1000)
                    }).build()
            )

            Image(painter = painter,
                contentDescription = article.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color(0x66030303)
                    )
            )
            Column(modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .align(Alignment.BottomStart)
                , verticalArrangement = Arrangement.Bottom) {
                article.title?.let {
                    Text(text = it,
                        style = TextStyle(color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold),
                        modifier = Modifier
                    )
                }
                if (article.author != null) {
                    Text(
                        text = article.author,
                        style = TextStyle(color = Color.White, fontSize = 12.sp),
                    )
                }else{
                    Text(
                        text = article.source.name,
                        style = TextStyle(color = Color.White, fontSize = 12.sp),
                    )
                }
            }

        }
    }
}
