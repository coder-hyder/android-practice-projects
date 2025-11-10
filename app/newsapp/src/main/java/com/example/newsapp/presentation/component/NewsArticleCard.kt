package com.example.newsapp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.Source

@Composable
fun NewsArticleCard(
    modifier: Modifier,
    onCardClicked: (Article) -> Unit,
    article: Article
) {



    Card(onClick = { onCardClicked(article) },
        shape = RoundedCornerShape(4.dp)
    ) {

        Column(modifier = Modifier.padding(12.dp)){
            ImageHolder(article = article)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = article.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = article.source.name,
                    style = MaterialTheme.typography.bodySmall)

                Text(text = article.publishedAt ?: "")
            }
        }
    }
}

@Composable
fun ImageHolder(
    article: Article,
    modifier : Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(article.urlToImage)
            .crossfade(true)
            .build(),
        contentDescription = "Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .aspectRatio(16/9f),
        placeholder = painterResource(R.drawable.placeholder_loading),
        error = painterResource(R.drawable.placeholder_news)
    )
    
}


//
////
//@Preview(showSystemUi = true)
//@Composable
//fun PreviewArticle(modifier: Modifier = Modifier) {
//    val articleData = Article(
//
//        author = "John Doe",
//        content = "Google announces a major AI breakthrough with its latest Gemini update, aiming to compete with OpenAI's GPT models.",
//        description = "Google's AI team unveiled the latest Gemini features that promise more natural conversations and advanced reasoning.",
//        publishedAt = "2025-11-10T10:30:00Z",
//        source = Source("1", "TechCrunch"),
//        title = "Google Unveils Gemini Update to Rival GPT Models",
//        url = "https://techcrunch.com/google-gemini-update",
//        urlToImage = ""
//    )
//
//
//    NewsArticleCard(
//        modifier = Modifier,
//        onCardClicked = {},
//        article = articleData
//    )
//
//}
//


