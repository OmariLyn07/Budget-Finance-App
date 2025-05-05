package com.example.budgetfinance.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class TransactionViewModel: ViewModel() {
    var description = { mutableStateOf("") }
    var amount = { mutableStateOf("") }
    var transactionList = mutableStateListOf<Transactions>()
        private set
    fun addTransaction(transaction: Transactions) {
        transactionList.add(transaction)
    }
    val totalAmount: Double
        get() = transactionList.sumOf { it.amount }
    val latestTransactionDate: LocalDate?
        get() = transactionList.maxByOrNull { it.date }?.date
    var date: LocalDate = LocalDate.now()
}