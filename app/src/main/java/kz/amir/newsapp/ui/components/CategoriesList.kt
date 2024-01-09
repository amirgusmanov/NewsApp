package kz.amir.newsapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.amir.newsapp.base.constants.Categories

typealias Category = String

@Composable
fun CategoriesList(
    categories: List<Category>,
    onCategorySelected: (String) -> Unit
) {
    AnimatedVisibility(visible = true) {
        LazyRow(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(categories) {
                CategoryItem(
                    category = it,
                    onCategorySelected = {
                        onCategorySelected(
                            it
                                .replaceFirst(Categories.regex, "")
                                .replaceFirstChar { firstWordChar -> firstWordChar.lowercase() }
                        )
                    }
                )
            }
        }
    }
}