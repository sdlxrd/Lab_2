package com.example.lab_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab_2.ui.theme.Lab_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab_2Theme {
                ToDoApp()
            }
        }
    }
}

@Composable
fun ToDoApp() {
    var taskText by remember { mutableStateOf("") }
    var tasks by remember { mutableStateOf(listOf<String>()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Ввід тексту
                BasicTextField(
                    value = taskText,
                    onValueChange = { taskText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    singleLine = true
                )

                // Кнопка додавання завдання
                Button(
                    onClick = {
                        if (taskText.isNotBlank()) {
                            tasks = tasks + taskText
                            taskText = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Додати завдання")
                }

                // Список завдань
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                ) {
                    items(tasks.size) { index ->
                        Text(
                            text = "${index + 1}. ${tasks[index]}",
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ToDoAppPreview() {
    Lab_2Theme {
        ToDoApp()
    }
}
