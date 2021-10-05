package com.aslnstbk.currencycoin.presentation.favorite_coins

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aslnstbk.currencycoin.presentation.coins_list.components.CoinListItem
import com.aslnstbk.currencycoin.presentation.common.Navigation

@Composable
fun FavoriteCoinsScreen(
    navController: NavController,
    viewModel: FavoriteCoinsViewModel = hiltViewModel()
) {
    val state = viewModel.favoriteCoins.value

    Scaffold {
        LazyColumn {
            items(state.favoriteCoins) { coin ->
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