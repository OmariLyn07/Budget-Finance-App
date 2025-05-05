package com.example.budgetfinance.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WalletViewModel: ViewModel() {
    var walletBalance by mutableFloatStateOf(0.0F)
}