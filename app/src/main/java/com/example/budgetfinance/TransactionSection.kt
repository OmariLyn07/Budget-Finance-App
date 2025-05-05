package com.example.budgetfinance

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.MonetizationOn
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.budgetfinance.data.TransactionViewModel
import com.example.budgetfinance.data.Transactions
import java.time.LocalDate


@Preview
@Composable
fun CallFinance(){
    val nav: NavController = rememberNavController()
    TransactionSection(nav)
}

@Composable
fun TransactionScreen(navController: NavController, transactionViewModel: TransactionViewModel){
    Scaffold(bottomBar = { BottomNavigationBar(navController) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            TransactionSection(navController, transactionViewModel)

        }
    }
}

@Composable
fun TransactionSection(navController: NavController, viewModel: TransactionViewModel = viewModel()) {
    val transaction = viewModel.description
    val amount = viewModel.amount
    val date = viewModel.date
    val transactions = viewModel.transactionList
    val totalAmount = transactions.sumOf {it.amount}
    var showDialog by remember { mutableStateOf(false) }

    Row {
        Text(
            text = "Transactions",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        SmallFloatingActionButton(onClick = { showDialog = true}) {
            Icon(Icons.Filled.Add, "Small Add Button")
        }
    }
    if(showDialog) {
        AddTransactionDialog(
            onAddTransaction = { newTransaction ->
                transactions.add(newTransaction)
                showDialog = false },
            onDismiss = { showDialog = false }
        )
    }
    LazyColumn {
        transactions.groupBy { it.date }.forEach { (date, groupedTransactions) ->
            item {
                Text(
                    text = "${date.toString()}: $${"%.2f".format(totalAmount)}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(groupedTransactions) {transaction ->
                TransactionCard(transaction)
            }
        }
    }
}


@Composable
fun AddTransactionDialog(onAddTransaction: (Transactions) -> Unit, onDismiss: () -> Unit) {
    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    val viewModel: TransactionViewModel = viewModel()


    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Transaction") },
        text = {
            Column {
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
                TextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Amount") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                if (description.isNotEmpty() && amount.isNotEmpty()) {
                    onAddTransaction(
                        Transactions(
                            description = description,
                            amount = amount.toDouble(),
                            date = LocalDate.now()
                        )

                    )
                    viewModel.addTransaction(Transactions(date = LocalDate.now(), amount = amount.toDouble(), description = description))
                }
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}


@Composable
fun TransactionCard(transaction: Transactions) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Description: ${transaction.description}")
            Text(text = "Amount: $${transaction.amount}")
        }
    }
}
