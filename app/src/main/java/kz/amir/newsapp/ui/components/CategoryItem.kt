package kz.amir.newsapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kz.amir.newsapp.ui.theme.PurpleLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(
    category: Category,
    onCategorySelected: () -> Unit
) {
    Card(
        modifier = Modifier.wrapContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = PurpleLight,
            contentColor = Color.White
        ),
        onClick = {
            onCategorySelected()
        }
    ) {
        BasicText(
            text = category,
            style = TextStyle.Default.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(10.dp)
        )
    }
}