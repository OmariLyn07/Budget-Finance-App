package com.example.budgetfinance.data

import java.time.LocalDate


data class Transactions (
    var description: String,
    var amount: Double,
    var date: LocalDate
)
