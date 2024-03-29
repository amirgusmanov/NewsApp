package kz.amir.newsapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.amir.newsapp.base.constants.Constants
import kz.amir.newsapp.ui.model.NewsUI
import kz.amir.newsapp.ui.theme.BackgroundWhite

@Composable
fun NewsList(
    news: List<NewsUI>,
    onArticleClicked: (NewsUI) -> Unit
) {
    AnimatedVisibility(visible = true) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 2.dp)
                .background(BackgroundWhite),
            content = {
                items(
                    news
                        .filterNot {
                            it.title == Constants.REMOVED_NEWS
                                    || it.urlToImage.isNullOrEmpty()
                                    || it.content.isNullOrEmpty()
                        }
                        .map { it.copy(content = it.content?.substringBefore("[")?.trim()) }
                ) { article ->
                    NewsItem(article = article) {
                        onArticleClicked.invoke(article)
                    }
                }
            }
        )
    }
}
