package com.example.budgetfinance.data

data class Bill(
    val billType: String,
    val billName: String,
    val amount: Double,
    val dueDate: String
)
