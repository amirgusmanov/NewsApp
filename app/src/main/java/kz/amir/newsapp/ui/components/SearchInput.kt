package kz.amir.newsapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kz.amir.newsapp.R
import kz.amir.newsapp.domain.model.SearchHistory
import kz.amir.newsapp.ui.screens.HomeViewModel
import kz.amir.newsapp.ui.theme.PurpleLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchInput(
    viewModel: HomeViewModel,
    searchKeyword: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }

    val searchHistory by viewModel.searchHistory.collectAsState()

    viewModel.getSearchHistory()

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        colors = SearchBarDefaults.colors(containerColor = PurpleLight),
        shape = MaterialTheme.shapes.medium,
        query = text,
        active = isActive,
        onQueryChange = { text = it },
        onActiveChange = { isActive = it },
        onSearch = {
            isActive = false
            searchKeyword.invoke(it)
        },
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search icon"
            )
        },
        trailingIcon = {
            if (isActive) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close icon",
                    modifier = Modifier.clickable {
                        if (text.isNotEmpty()) {
                            text = ""
                        } else {
                            isActive = false
                        }
                    }
                )
            }
        }
    ) {
        when (searchHistory) {
            is HomeViewModel.SearchState.Result -> {
                SearchHistoryItem(
                    items = (searchHistory as HomeViewModel.SearchState.Result).data ?: emptyList(),
                    onClick = {
                        isActive = false
                        searchKeyword.invoke(it)
                    }
                )
            }

            HomeViewModel.SearchState.ShowLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(trackColor = Color.Black)
                }
            }
        }
    }
}

@Composable
fun SearchHistoryItem(
    items: List<SearchHistory>,
    onClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        content = {
            items(items) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 50.dp)
                        .clickable { it.title?.let { title -> onClick.invoke(title) } },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_history),
                        contentDescription = "Search history icon"
                    )

                    Spacer(modifier = Modifier.width(24.dp))

                    Text(text = it.title ?: "")

                    Spacer(modifier = Modifier.weight(1f))

                    SearchHistoryImage(
                        articleImageUrl = it.imageUrl ?: "",
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .width(40.dp)
                            .height(40.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.ic_search_arrow),
                        contentDescription = "Arrow icon"
                    )
                }
            }
        }
    )
}

@Composable
fun SearchHistoryImage(
    articleImageUrl: String,
    modifier: Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .error(LocalContext.current.getDrawable(R.drawable.ic_logo))
            .fallback(LocalContext.current.getDrawable(R.drawable.ic_logo))
            .data(articleImageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "Search history image",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}