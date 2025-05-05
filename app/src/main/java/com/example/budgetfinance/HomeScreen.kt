package com.example.budgetfinance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.budgetfinance.data.BillViewModel
import com.example.budgetfinance.data.WalletViewModel

@Preview
@Composable
fun callHome(){
    val nav: NavController = rememberNavController()
    val billingViewModel: BillViewModel = BillViewModel()
    val walletViewModel: WalletViewModel = WalletViewModel()
    HomeScreen(nav, walletViewModel, billingViewModel)
}


@Composable
fun HomeScreen(navController: NavController, walletViewModel: WalletViewModel, billingViewModel: BillViewModel) {
    Scaffold(bottomBar = { BottomNavigationBar(navController) }){ padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)){
            WalletSection(walletViewModel)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 520.dp),
                contentAlignment = Alignment.Center
            ) {
                BillingSection(billingViewModel)
            }
            CurrencySection()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
