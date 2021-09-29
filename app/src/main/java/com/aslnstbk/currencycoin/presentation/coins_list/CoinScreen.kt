package com.aslnstbk.currencycoin.presentation.coins_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aslnstbk.currencycoin.presentation.coins_list.components.CoinListItem
import com.aslnstbk.currencycoin.presentation.common.Navigation
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinsListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(0)
                    }
                }
            ) {
                Text(text = "Top")
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(state.coins.size - 1)
                    }
                }
            ) {
                Text(text = "Bottom")
            }
        }

        LazyColumn(state = scrollState) {
            items(state.coins) { coin ->
                CoinListItem(
                    coin = coin,
                    onItemClick = {
                        navController.navigate(route = Navigation.CoinDetail.route + "/${coin.id}")
                    }
                )
            }
        }
    }
}