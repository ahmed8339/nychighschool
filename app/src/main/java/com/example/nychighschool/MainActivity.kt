package com.example.nychighschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.nychighschool.ui.theme.NYCHighSchoolTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NYCHighSchoolTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    //SchoolListItem(school = School("Liberation Diploma Plus High School", "261", "oVERvIEW"))
                    val viewModel: SchoolViewModel = viewModel<SchoolViewModel>()
                    HighSchoolListScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun HighSchoolListScreen(viewModel: SchoolViewModel = viewModel()){
    val schools by viewModel.schools.collectAsState()

    Column {
        if (schools.isEmpty()) {
            // Loading state
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyColumn {
                items(schools) { school ->
                    SchoolListItem(school, viewModel = viewModel)
                }
            }
        }
    }
}


@Composable
fun SchoolListItem(school: School, viewModel: SchoolViewModel){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable {
            viewModel.selectSchool(school) }
    )
    {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = school.school_name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = school.dbn)
        }
    }
    
}

@Composable
fun SchoolDetailScreen(viewModel: SchoolViewModel) {
    val selectedSchool by viewModel.selectedSchool.collectAsState()

    if (selectedSchool != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            selectedSchool?.overview_paragraph?.let { Text(text = it) }
            Spacer(modifier = Modifier.height(8.dp))
        }
    } else {
        // Handle case where no school is selected
        Text("No school selected")
    }
}

