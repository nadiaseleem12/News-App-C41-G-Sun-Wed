package com.route.newsappc41gsunwed.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.newsappc41gsunwed.R
import com.route.newsappc41gsunwed.SettingsViewModel
import com.route.newsappc41gsunwed.utils.Constants


@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel,
    onGoToHomeClick: () -> Unit
) {

    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    val screenHeightDp = LocalConfiguration.current.screenHeightDp
    val isLandScape = screenWidthDp > screenHeightDp
    val drawerWidthPercent = if (isLandScape) 0.3f else 0.7f
    ModalDrawerSheet(
        modifier = Modifier.fillMaxWidth(drawerWidthPercent),
        drawerContainerColor = MaterialTheme.colorScheme.background
    ) {

        Column(horizontalAlignment = Alignment.Start) {

            Box(
                modifier = Modifier
                    .fillMaxHeight(.2f)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onBackground),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    stringResource(R.string.app_name),
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight.Bold
                )

            }

            Spacer(modifier = Modifier.height(16.dp))
            NavigationDrawerItem(
                icon = R.drawable.ic_home,
                label = stringResource(R.string.go_to_home)
            ) {
                onGoToHomeClick()
            }
            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(thickness = 1.dp, modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.height(16.dp))
            NavigationDrawerItem(icon = R.drawable.ic_theme, label = stringResource(R.string.theme))

            Spacer(modifier = Modifier.height(16.dp))

            ThemeModeDropDownMenu(viewModel = viewModel)

            HorizontalDivider(thickness = 1.dp, modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.height(16.dp))


            NavigationDrawerItem(
                icon = R.drawable.ic_langauge,
                label = stringResource(R.string.language)
            )
            LanguageDropDownMenu(viewModel = viewModel)


        }

    }
}

@Composable
fun ThemeModeDropDownMenu(modifier: Modifier = Modifier, viewModel: SettingsViewModel) {
    val themeMap = mapOf(
        Constants.LIGHT_MODE to stringResource(R.string.light_mode),
        Constants.DARK_MODE to stringResource(R.string.dark_mode),
        Constants.SYSTEM_MODE to stringResource(R.string.system_mode)
    )

    GenericDropDownMenu(
        expanded = viewModel.themeExpanded.value,
        selectedItem = viewModel.selectedTheme.value,
        onExpandedChange = {expanded-> viewModel.setThemeExpanded(expanded) },
        onItemSelected = {key: String, _: String ->  viewModel.applyTheme(key) },
        menuItems = themeMap
    )
}

@Composable
fun LanguageDropDownMenu(modifier: Modifier = Modifier,viewModel: SettingsViewModel) {

    val languageMap = mapOf(
        "ar" to stringResource(R.string.arabic),
        "en" to stringResource(R.string.english)
    )
    GenericDropDownMenu(
        expanded = viewModel.languageExpanded.value,
        selectedItem = viewModel.selectedLanguage.value,
        onExpandedChange = {expanded-> viewModel.setLanguageExpanded(expanded) },
        onItemSelected = { key: String, value: String -> viewModel.setSelectedLanguage(value,key)},
        menuItems = languageMap
    )

}


@Composable
fun NavigationDrawerItem(
    modifier: Modifier = Modifier,
    icon: Int,
    label: String,
    onClick: () -> Unit = {}
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }) {

        Image(
            painter = painterResource(icon),
            contentDescription = label,
            modifier = Modifier.padding(8.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
        )
        Text(text = label, modifier = Modifier.padding(start = 6.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericDropDownMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    selectedItem: String,
    onExpandedChange: (Boolean) -> Unit,
    menuItems: Map<String, String>,
    onItemSelected: (key: String,value:String) -> Unit
) {
    ExposedDropdownMenuBox(
        expanded = expanded, onExpandedChange = { onExpandedChange(it) },
        modifier = Modifier
            .padding(10.dp)
            .border(
                1.dp, MaterialTheme.colorScheme.onBackground,
                RoundedCornerShape(16.dp)
            )
    ) {
        TextField(value = selectedItem, onValueChange = {}, readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = Color.LightGray,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ), trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            })

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { onExpandedChange(false) }) {
            menuItems.forEach {
                DropdownMenuItem(text = { Text(text = it.value) }, onClick = {
                    onItemSelected(it.key,it.value)
                    onExpandedChange(false)
                }, contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding)
            }
        }


    }
}

@Preview(showSystemUi = true)
@Composable
private fun DrawerContentPreview() {
    DrawerContent(viewModel = SettingsViewModel(), onGoToHomeClick = {})
}