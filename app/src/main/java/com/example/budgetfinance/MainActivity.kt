package com.example.budgetfinance


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.budgetfinance.data.BillViewModel
import com.example.budgetfinance.data.NavScreen
import com.example.budgetfinance.data.TransactionViewModel
import com.example.budgetfinance.data.WalletViewModel
import com.example.budgetfinance.ui.theme.BudgetFinanceTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BudgetFinanceTheme {
                val billingViewModel: BillViewModel = viewModel()
                val transactionViewModel: TransactionViewModel = viewModel()
                val walletViewModel: WalletViewModel = viewModel()
                SetBarColor(color = MaterialTheme.colorScheme.background)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        modifier = Modifier,
                        startDestination = NavScreen.navHome,
                        builder = {
                            composable(NavScreen.navHome){
                                HomeScreen(navController, walletViewModel, billingViewModel, transactionViewModel)
                            }
                            composable(NavScreen.navFinance) {
                                TransactionScreen(
                                    navController,
                                    transactionViewModel = transactionViewModel
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun SetBarColor(color: Color) {
    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(color = color)
    }
}

