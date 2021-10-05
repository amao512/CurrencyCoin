package com.aslnstbk.currencycoin.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aslnstbk.currencycoin.presentation.coin_detail.CoinDetailScreen
import com.aslnstbk.currencycoin.presentation.coins_list.CoinListScreen
import com.aslnstbk.currencycoin.presentation.common.Navigation
import com.aslnstbk.currencycoin.presentation.favorite_coins.FavoriteCoinsScreen
import com.aslnstbk.currencycoin.presentation.ui.theme.CurrencyCoinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyCoinTheme(darkTheme = true) {
                Surface(color = MaterialTheme.colors.background) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        NavHost(
            navController = navController,
            startDestination = Navigation.CoinList.route
        ) {
            composable(route = Navigation.CoinList.route) {
                CoinListScreen(navController = navController)
            }
            composable(route = Navigation.CoinDetail.route + "/{coinId}") {
                CoinDetailScreen {
                    navController.navigateUp()
                }
            }
            composable(route = Navigation.FavoriteCoins.route) {
                FavoriteCoinsScreen(navController = navController)
            }
        }
    }
}