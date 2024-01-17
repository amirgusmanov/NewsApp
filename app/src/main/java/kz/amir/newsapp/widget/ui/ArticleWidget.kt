package kz.amir.newsapp.widget.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.wrapContentHeight
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import kz.amir.newsapp.R
import kz.amir.newsapp.ui.MainActivity
import kz.amir.newsapp.widget.NewsWidget

@SuppressLint("RestrictedApi")
@Composable
fun Article(uiState: NewsWidget.WidgetUIState) {
    Column(
        modifier = GlanceModifier
            .background(Color.White)
            .fillMaxSize()
            .cornerRadius(10.dp)
            .clickable(actionStartActivity(activity = MainActivity::class.java)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            provider = ImageProvider(R.drawable.ic_launcher_foreground),
            contentDescription = "News image",
            modifier = GlanceModifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(4.dp),
            contentScale = ContentScale.Crop,
        )

        Text(
            text = "Title",
            style = TextStyle(
                color = ColorProvider(R.color.black),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 4.dp),
            maxLines = 1
        )

        Text(
            text = "Description",
            style = TextStyle(
                color = ColorProvider(R.color.black),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            ),
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            maxLines = 1
        )
    }
}