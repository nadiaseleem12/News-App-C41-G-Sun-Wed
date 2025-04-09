package com.route.newsappc41gsunwed.news

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.newsappc41gsunwed.widgets.ErrorDialog
import com.route.newsappc41gsunwed.widgets.NewsCard
import com.route.newsappc41gsunwed.widgets.NewsList
import com.route.newsappc41gsunwed.widgets.NewsToolbar
import com.route.data.models.ArticlesItemEntity
import com.route.data.models.SourcesItemEntity

// News Screen -> MVVM
@Composable
fun NewsScreen(
    endpointId: String,
    viewModel: NewsViewModel = viewModel(),
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    onSearchClick: () -> Unit,
) {
    val sourcesList = viewModel.sourcesListStates
    val newsList = viewModel.newsListStates
    LaunchedEffect(Unit) {
        viewModel.getSources(endpointId)
    }
    LaunchedEffect(viewModel.selectedSourceId.value) {
        viewModel.getNewsBySource()
    }
    Scaffold(
        topBar = {
            NewsToolbar(title = "General", drawerState = drawerState) {
                onSearchClick()
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(modifier.padding(paddingValues)) {
            if (sourcesList.isNotEmpty())
                SourcesTabRow(
                    sourcesList = sourcesList,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    newsList.clear()
                    viewModel.selectedSourceId.value = it
                }
            NewsList(viewModel)
        }
    }

    if (viewModel.isLoading.value)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.onBackground)
        }

    if (viewModel.errorState.value.isNotEmpty())
        ErrorDialog(viewModel.errorState.value) {
            viewModel.errorState.value = ""
        }
}



@Preview(showSystemUi = true)
@Composable
private fun NewsCardPreview() {
    NewsCard(
        articleItem = ArticlesItemEntity(
            author = "Jon Haworth",
            title = "40-year-old man falls 200 feet to his death while canyoneering at national park",

            )
    )
}

@Composable
fun SourcesTabRow(
    sourcesList: List<SourcesItemEntity>,
    modifier: Modifier = Modifier,
    onSourceSelected: (id: String) -> Unit
) {
    // Create State ->
    val selectedItemIndex = remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(Unit) {
        onSourceSelected(sourcesList.get(0).id ?: "")
    }
    val color = MaterialTheme.colorScheme.onBackground
    val selectedModifier = Modifier.drawBehind {
        val strokeWidthPx = 2.dp.toPx()
        val verticalOffset = size.height - 2.sp.toPx()
        drawLine(
            color = color ,
            strokeWidth = strokeWidthPx,
            start = Offset(0f, verticalOffset),
            end = Offset(size.width, verticalOffset)
        )
    }
    LazyRow(modifier.background(MaterialTheme.colorScheme.background)) {
        itemsIndexed(sourcesList) { index, sourceItem ->
            Tab(
                selectedContentColor = MaterialTheme.colorScheme.onBackground,
                unselectedContentColor = MaterialTheme.colorScheme.onBackground,
                selected = selectedItemIndex.intValue == index,
                onClick = {
                    Log.e("TAG", "SourcesTabRow:  $index")
                    onSourceSelected(sourceItem.id ?: "")
                    selectedItemIndex.intValue = index
                },
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = sourceItem.name ?: "",
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = if (selectedItemIndex.intValue == index) selectedModifier else Modifier
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun SourcesTabRowPreview() {
    SourcesTabRow(
        sourcesList = listOf(
            SourcesItemEntity(name = "ABC News"),
            SourcesItemEntity(name = "Al-Jazeera"),
            SourcesItemEntity(name = "BBC News")
        )
    ) {

    }
}
