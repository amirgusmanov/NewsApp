package kz.amir.newsapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kz.amir.newsapp.ui.components.AuthorComponent
import kz.amir.newsapp.ui.components.NewsImage
import kz.amir.newsapp.ui.components.TimeComponent
import kz.amir.newsapp.ui.model.NewsUI
import kz.amir.newsapp.ui.navigation.Screen
import kz.amir.newsapp.ui.theme.BackgroundWhite
import kz.amir.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailsScreen(
    navController: NavController,
    article: NewsUI,
    isSaved: Boolean
) {
    //todo: handle viewModel states correctly
    val viewModel: DetailsViewModel = viewModel()
    val uiState = viewModel.state.collectAsState()

    var isSavedArticle by rememberSaveable { mutableStateOf(isSaved) }

    BackHandler {
        passDataBack(navController, needsToUpdate = isSavedArticle != isSaved)
        navController.popBackStack(route = Screen.Home.route, inclusive = false)
    }

    Column(
        modifier = Modifier.verticalScroll(
            state = rememberScrollState(),
            enabled = true
        )
    ) {
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
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .clip(shape = MaterialTheme.shapes.medium)
        )

        //Content
        BasicText(
            text = article.content ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        Row {
            //Save button
            OutlinedButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp, end = 2.5.dp),
                text = if (isSavedArticle) "Remove" else "Save",
                onClicked = {
                    isSavedArticle = if (isSavedArticle) {
                        article.title?.let { viewModel.deleteArticle(it) }
                        false
                    } else {
                        viewModel.saveArticle(article = article.mapTo())
                        true
                    }
                }
            )

            //Back button
            OutlinedButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 2.5.dp, end = 10.dp),
                text = "Back",
                onClicked = {
                    passDataBack(navController, needsToUpdate = isSavedArticle != isSaved)
                    navController.popBackStack(route = Screen.Home.route, inclusive = false)
                }
            )
        }
    }
}

@Composable
fun OutlinedButton(
    modifier: Modifier,
    text: String,
    onClicked: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraSmall,
        colors = ButtonDefaults.buttonColors(containerColor = BackgroundWhite),
        border = BorderStroke(width = 1.dp, color = Color.Black),
        onClick = {
            onClicked.invoke()
        }
    ) {
        BasicText(
            text = text,
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
    }
}

private fun passDataBack(navController: NavController, needsToUpdate: Boolean) {
    navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.set("needsToUpdate", needsToUpdate)
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
                content = "Writing a news article is different from writing other articles or informative pieces because news articles present information."
            ), remember { false }
        )
    }
}