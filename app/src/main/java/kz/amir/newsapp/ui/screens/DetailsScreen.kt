package kz.amir.newsapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kz.amir.newsapp.ui.components.AuthorComponent
import kz.amir.newsapp.ui.components.NewsImage
import kz.amir.newsapp.ui.components.TimeComponent
import kz.amir.newsapp.ui.model.NewsUI
import kz.amir.newsapp.ui.navigation.Screen
import kz.amir.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailsScreen(
    navController: NavController,
    article: NewsUI
) {
    BackHandler {
        navController.popBackStack(route = Screen.Home.route, inclusive = false)
    }

    Column {
        //Title
        BasicText(
            text = article.title ?: "",
            style = MaterialTheme.typography.headlineSmall
                .copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(top = 20.dp, bottom = 10.dp)
        )

        //Author and time
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AuthorComponent(article = article)

            Spacer(modifier = Modifier.width(4.dp))

            TimeComponent(article = article)
        }

        //Description
        BasicText(
            text = article.description ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            style = MaterialTheme.typography.bodyMedium
                .copy(fontSize = 15.sp)
        )

        //Image
        NewsImage(
            article = article,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(horizontal = 10.dp)
                .clip(shape = MaterialTheme.shapes.small)
        )

        //Content
        BasicText(
            text = article.content ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun DetailsScreenPreview() {
    NewsAppTheme {
        DetailsScreen(
            rememberNavController(), NewsUI(
                sourceName = "bbc",
                author = "John Doe",
                title = "Bring Me The Horizon Talk headlining Reading & Leeds 2023",
                description = "This is a sample news description. It can contain some details about the news.",
                url = "https://www.google.com",
                urlToImage = "https://www.example.com/image.jpg",
                publishedAt = "2023-01-01T12:34:56Z",
                content = "Writing a news article is different from writing other articles or informative pieces because news articles present information in a specific way. It's important to be able to convey all the relevant information in a limited word count and give the facts to your target audience concisely. Knowing how to write a news article can help a career in journalism, develop your writing skills and help you convey information clearly and concisely."
            )
        )
    }
}