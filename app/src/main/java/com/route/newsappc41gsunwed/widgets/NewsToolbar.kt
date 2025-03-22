package com.route.newsappc41gsunwed.widgets
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.newsappc41gsunwed.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsToolbar(
    title: String,
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(1.dp))
                Text(text = title)
                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(
                        R.string.news_search
                    ),
                    modifier = Modifier.clickable {
                        onSearchClick()
                    },
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)

                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        ),
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = stringResource(R.string.navigation_menu_icon),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        },
    )
}

@Preview
@Composable
private fun NewsToolbarPreview() {
    NewsToolbar(title = "General"){}
}
