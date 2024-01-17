package kz.amir.newsapp.widget

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import kz.amir.newsapp.R
import kz.amir.newsapp.ui.theme.NewsAppTheme
import kz.amir.newsapp.widget.ui.Article

class NewsWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            NewsAppTheme {
                Content(context)
            }
        }
    }

    @SuppressLint("RestrictedApi")
    @Composable
    private fun Content(context: Context) {
        //todo: read about data store, write logic for work manager to fetch data from api
        //todo: make widget design like a card in news list
        //todo: think about scrollable news in widget

        val someValFromPrefs: String? = null

        val articlesWidgetUIState = WidgetUIState.ArticlesWidgetUIState(
            title = someValFromPrefs ?: context.getString(R.string.fetch_alert_message),
            description = someValFromPrefs ?: context.getString(R.string.empty_description),
            imageUrl = someValFromPrefs ?: ""
        )

        Article(articlesWidgetUIState)
    }

    sealed interface WidgetUIState {
        data class ArticlesWidgetUIState(
            val title: String,
            val description: String,
            val imageUrl: String
        ) : WidgetUIState
    }
}