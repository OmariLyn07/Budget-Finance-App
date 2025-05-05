package com.example.budgetfinance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetfinance.data.Bill
import com.example.budgetfinance.data.BillViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Preview
@Composable
fun BillingSection(viewModel: BillViewModel = viewModel()) {
    val billType = viewModel.billType
    val billName = viewModel.billName
    val billAmount = viewModel.billAmount
    val billDueDate = viewModel.billDueDate
    val billList = viewModel.billList
    var showDialog by remember { mutableStateOf(false) }


    Column (modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { showDialog = true }) {
            Text("Add Bill")
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Enter Bill Details") },
                text = {
                    Column {
                        BasicTextField(
                            value = billType.value,
                            onValueChange = { billType.value = it },
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            decorationBox = { innerTextField ->
                                Box(Modifier.padding(8.dp)) {
                                    if (billType.value.isEmpty()) {
                                        Text(text = "Bill Type", fontSize = 16.sp)
                                    }
                                    innerTextField()
                                }
                            }
                        )
                        BasicTextField(
                            value = billName.value,
                            onValueChange = { billName.value = it },
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            decorationBox = { innerTextField ->
                                Box(Modifier.padding(8.dp)) {
                                    if (billName.value.isEmpty()) {
                                        Text(text = "Bill Name", fontSize = 16.sp)
                                    }
                                    innerTextField()
                                }
                            }
                        )
                        BasicTextField(
                            value = billAmount.value,
                            onValueChange = { billAmount.value = it },
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            decorationBox = { innerTextField ->
                                Box(Modifier.padding(8.dp)) {
                                    if (billAmount.value.isEmpty()) {
                                        Text(text = "Bill Amount", fontSize = 16.sp)
                                    }
                                    innerTextField()
                                }
                            }
                        )
                        BasicTextField(
                            value = billDueDate.value,
                            onValueChange = { billDueDate.value = it },
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            decorationBox = { innerTextField ->
                                Box(Modifier.padding(8.dp)) {
                                    if (billDueDate.value.isEmpty()) {
                                        Text(text = "Bill Due Date", fontSize = 16.sp)
                                    }
                                    innerTextField()
                                }
                            }
                        )
                    }

                },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        if(billType.value.isNotEmpty() && billName.value.isNotEmpty() && billAmount.value.isNotEmpty() && billDueDate.value.isNotEmpty()) {
                            billList.add( Bill(billType = billType.value, billName = billName.value, amount = billAmount.value.toDouble(), dueDate = billDueDate.value))
                            billType.value = ""
                            billName.value = ""
                            billAmount.value = ""
                            billDueDate.value = ""
                            }
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }


        LazyColumn { items(billList.size) { index ->
            BillItem(billList[index], onDelete = { bill -> billList.remove(bill)})} }
    }
}

@Composable
fun BillItem(bill: Bill, onDelete: (Bill) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row{
                Text(text = bill.billType, fontSize = 16.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Due Date: ${bill.dueDate}", fontSize = 16.sp)
            }
            Row{
                Text(text = bill.billName, fontSize = 16.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "${bill.amount}", fontSize = 16.sp)
            }
            Button(onClick = { onDelete(bill)}) { Text("Delete")}
        }
    }
}
