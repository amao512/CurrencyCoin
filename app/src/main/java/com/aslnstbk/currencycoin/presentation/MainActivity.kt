package com.aslnstbk.currencycoin.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Coins")
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Filled.Favorite, contentDescription = null)
                        }
                    }
                )
            }
        ) {
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