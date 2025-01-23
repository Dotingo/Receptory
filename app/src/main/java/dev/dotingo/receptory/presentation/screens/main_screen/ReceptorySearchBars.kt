package dev.dotingo.receptory.presentation.screens.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import dev.dotingo.receptory.ui.theme.Dimens.mediumIconSize
import dev.dotingo.receptory.ui.theme.Dimens.extraSmallPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.ui.icons.FilterArrowIcon
import dev.dotingo.receptory.ui.icons.SearchIcon
import dev.dotingo.receptory.ui.icons.favorite.FavoriteBoldIcon
import dev.dotingo.receptory.ui.icons.favorite.FavoriteOutlinedIcon
import dev.dotingo.receptory.ui.theme.favoriteColor


@Composable
fun RecipeSearchBar(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String,
    onTextChange: (String) -> Unit,
    onClearClicked: () -> Unit,
    onSortFilterClicked: (Boolean) -> Unit,
    onFavoriteFilterClicked: (Boolean) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var isFavoriteOn by remember { mutableStateOf(false) }
    var isSortFilterOpen by remember { mutableStateOf(false) }

    Column(modifier.padding(horizontal = smallPadding)) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .focusRequester(focusRequester),
                value = text,
                onValueChange = {
                    onTextChange(it)
                },
                placeholder = {
                    Text(
                        text = placeholder,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                ),
                leadingIcon = {
                    IconButton(onClick = {
                        focusRequester.requestFocus()
                    }) {
                        Icon(
                            imageVector = SearchIcon,
                            contentDescription = null
                        )
                    }
                },
                trailingIcon = {
                    if (text.isNotBlank()) {
                        IconButton(onClick = {
                            onClearClicked()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = null,
                                modifier = Modifier.size(mediumIconSize)
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        focusManager.clearFocus()
                    }
                ),
                singleLine = true
            )
            IconButton(onClick = {
                isSortFilterOpen = !isSortFilterOpen
                onSortFilterClicked(isSortFilterOpen)
            }) {
                Icon(FilterArrowIcon, "Фильтр сортировки")
            }
            IconButton(onClick = {
                isFavoriteOn = !isFavoriteOn
                onFavoriteFilterClicked(isFavoriteOn)
            }) {
                Icon(
                     if (isFavoriteOn) FavoriteBoldIcon else FavoriteOutlinedIcon,
                    "Фильтр фаворитов",
                    tint = favoriteColor
                )
            }

        }
        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.offset(y = -extraSmallPadding)
        )
    }
}