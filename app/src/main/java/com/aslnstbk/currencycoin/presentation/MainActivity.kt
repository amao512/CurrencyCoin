package com.aslnstbk.currencycoin.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aslnstbk.currencycoin.presentation.coin_detail.CoinDetailScreen
import com.aslnstbk.currencycoin.presentation.coins_list.CoinListScreen
import com.aslnstbk.currencycoin.presentation.common.Navigation
import com.aslnstbk.currencycoin.presentation.ui.theme.CurrencyCoinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyCoinTheme(darkTheme = true) {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Navigation.CoinList.route
                    ) {
                        composable(Navigation.CoinList.route) {
                            CoinListScreen(navController = navController)
                        }
                        composable(Navigation.CoinDetail.route + "/{coinId}") {
                            CoinDetailScreen()
                        }
                    }
                }
            }
        }
    }
}