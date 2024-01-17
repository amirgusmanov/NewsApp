package kz.amir.newsapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kz.amir.newsapp.R
import kz.amir.newsapp.base.util.formatDateString
import kz.amir.newsapp.ui.model.NewsUI
import kz.amir.newsapp.ui.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsItem(
    article: NewsUI,
    onItemClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        onClick = { onItemClicked.invoke() },
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
        ) {
            // Image
            NewsImage(
                url = article.urlToImage,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Title
            BasicText(
                text = article.title ?: "",
                maxLines = 2,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Author
            AuthorComponent(article = article)

            Spacer(modifier = Modifier.height(4.dp))

            // Time
            TimeComponent(article = article)
        }
    }
}

@Composable
fun NewsImage(
    url: String?,
    modifier: Modifier,
) {
    val errorDrawable = LocalContext.current.getDrawable(R.drawable.ic_no_photo)
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .fallback(errorDrawable)
            .error(errorDrawable)
            .crossfade(true)
            .build(),
        contentDescription = "Article main image",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
fun AuthorComponent(article: NewsUI) {
    Card(
        modifier = Modifier
            .wrapContentWidth()
            .padding(4.dp)
            .background(Color.Black, shape = MaterialTheme.shapes.extraSmall),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        BasicText(
            text = article.sourceName ?: "",
            style = MaterialTheme.typography.bodyMedium
                .copy(color = Color.White),
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
        )
    }
}

@Composable
fun TimeComponent(article: NewsUI) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_date),
            contentDescription = null,
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        BasicText(
            text = article.publishedAt.formatDateString(),
            style = MaterialTheme.typography.bodyMedium
                .copy(color = Color.DarkGray),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun NewsItemPreview() {
    val dummyArticle = NewsUI(
        sourceName = "bbc",
        author = "John Doe",
        title = "Sample News Title",
        description = "This is a sample news description. It can contain some details about the news. fa;sdlfkajdsflajdf;ladjfal;dsgjafljasfldvjas;ldjvzcxlknvzcxvnaknfdsjfalkds;jfalkds;fjadsl;fjadsf",
        url = "https://www.google.com",
        urlToImage = "https://www.example.com/image.jpg",
        publishedAt = "2023-01-01T12:34:56Z",
        content = "This is the content of the news article."
    )
    NewsAppTheme {
        NewsItem(article = dummyArticle) {}
    }
}