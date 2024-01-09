package kz.amir.newsapp.ui.components

import androidx.compose.foundation.BorderStroke
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
import kz.amir.newsapp.ui.theme.Pink100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(
    category: Category,
    onCategorySelected: () -> Unit
) {
    Card(
        modifier = Modifier.wrapContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = Pink100,
            contentColor = Color.White
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color.Gray
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