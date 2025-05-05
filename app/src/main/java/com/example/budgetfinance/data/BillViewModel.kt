package com.example.budgetfinance.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BillViewModel : ViewModel() {
    var billType = mutableStateOf("")
    var billName = mutableStateOf("")
    var billAmount = mutableStateOf("")
    var billDueDate = mutableStateOf("")
    val billList = mutableStateListOf<Bill>()
}