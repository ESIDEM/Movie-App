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
import com.xtremepixel.movieapp.model.Movie
import com.xtremepixel.movieapp.model.getMovies
import com.xtremepixel.movieapp.screens.MovieScreens
import com.xtremepixel.movieapp.widgets.MovieItem

@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Gray, elevation = 5.dp) {
            Text(text = "Movies")
        }
    }) {

        MainContent(navController = navController)

    }
}

@Composable
fun MainContent(
    navController: NavController,
    movies: List<Movie> = getMovies()
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