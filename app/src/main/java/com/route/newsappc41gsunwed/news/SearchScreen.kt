package com.route.newsappc41gsunwed.news

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.newsappc41gsunwed.R
import com.route.newsappc41gsunwed.widgets.ErrorDialog
import com.route.newsappc41gsunwed.widgets.NewsList

@Composable
fun SearchScreen(viewModel: NewsViewModel = viewModel(), modifier: Modifier = Modifier) {
    val searchTFFocusRequester = remember { FocusRequester() }

    val focusManager = LocalFocusManager.current
    LaunchedEffect(true) {
        searchTFFocusRequester.requestFocus()
    }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            TextField(
                value = viewModel.searchQuery.value,
                onValueChange = { newValue ->
                    viewModel.searchQuery.value = newValue
                },
                maxLines = 1,
                placeholder = {
                    Text(text = stringResource(R.string.search))
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedTextColor = Color.LightGray,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.onBackground
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .border(
                        1.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(16.dp)
                    )
                    .focusRequester(searchTFFocusRequester)
                    .onFocusChanged { focusState ->
                        viewModel.isFocused.value = focusState.isFocused
                    },
                trailingIcon = {
                    if (viewModel.isFocused.value)
                        Image(
                            imageVector = Icons.Default.Close,
                            contentDescription = "close",
                            colorFilter = ColorFilter.tint(
                                color = MaterialTheme.colorScheme.onBackground,
                                blendMode = BlendMode.SrcAtop
                            ),
                            modifier = Modifier.clickable {
                                if (viewModel.searchQuery.value.isNotEmpty())
                                    viewModel.searchQuery.value = ""
                                else
                                    focusManager.clearFocus()
                            })
                },
                leadingIcon = {
                    Image(
                        imageVector = Icons.Default.Search,
                        contentDescription = "clear",
                        colorFilter = ColorFilter.tint(
                            color = MaterialTheme.colorScheme.onBackground,
                            blendMode = BlendMode.SrcAtop
                        ),
                        modifier = Modifier.clickable {
                            viewModel.getNews()
                        }
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(onSearch = {
                    viewModel.getNews()
                })
            )

            NewsList(viewModel)
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
    }
}