package com.xtremepixel.movieapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.xtremepixel.movieapp.MovieItem
import com.xtremepixel.movieapp.screens.MovieScreens

@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Magenta, elevation = 5.dp) {
            Text(text = "Movies")
        }
    }) {

        MainContent(navController = navController)

    }
}

@Composable
fun MainContent(
    navController: NavController,
    movies: List<String> = listOf(
        "Sam", "Esidem", "New Series", "Joe", "Caleb",
        "Sam", "Esidem", "New Series", "Joe", "Caleb"
    )
) {

    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = movies) {
                MovieItem(item = it) { movie ->
                    navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movie")
                }
            }
        }
    }
}