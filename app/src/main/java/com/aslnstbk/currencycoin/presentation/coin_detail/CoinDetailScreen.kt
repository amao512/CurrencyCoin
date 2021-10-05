package com.aslnstbk.currencycoin.presentation.coin_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aslnstbk.currencycoin.presentation.coin_detail.components.CoinTagComponent
import com.aslnstbk.currencycoin.presentation.coin_detail.components.CoinTeam
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {
    val state = viewModel.state.value
    var isFavorite: Boolean =  state.coin?.isFavorite ?: false

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = state.coin?.id.orEmpty())
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.onFavoriteButtonClick(state.coin)
                        isFavorite = !isFavorite
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null,
                            tint =  if (isFavorite) Color.Red else Color.White
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClicked() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(all = 16.dp)
        ) {
            LazyColumn {
                state.coin?.let { coin ->
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                                style = MaterialTheme.typography.h5,
                                modifier = Modifier.weight(8f)
                            )
                            Text(
                                text = if (coin.isActive) "active" else "inactive",
                                color = if (coin.isActive) Color.Green else Color.Red,
                                style = MaterialTheme.typography.body2,
                                fontStyle = FontStyle.Italic,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .align(CenterVertically)
                                    .weight(2f)
                            )
                        }
                        Text(
                            text = coin.description,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )

                        if (coin.tags.isNotEmpty()) {
                            Text(
                                text = "Tags",
                                style = MaterialTheme.typography.subtitle1
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            FlowRow(
                                mainAxisSpacing = 10.dp,
                                crossAxisSpacing = 10.dp,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                coin.tags.forEach {
                                    CoinTagComponent(coinTag = it)
                                }
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                        }

                        if (coin.team.isNotEmpty()) {
                            Text(
                                text = "Team",
                                style = MaterialTheme.typography.subtitle1
                            )
                        }
                    }

                    items(coin.team) { team ->
                        CoinTeam(
                            team = team,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                        Divider()
                    }
                }
            }
        }
    }
}