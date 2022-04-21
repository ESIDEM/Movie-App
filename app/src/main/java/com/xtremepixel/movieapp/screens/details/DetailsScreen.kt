package com.xtremepixel.movieapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.xtremepixel.movieapp.model.Movie
import com.xtremepixel.movieapp.model.getMovies
import com.xtremepixel.movieapp.widgets.MovieItem

@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {

    val movieList = getMovies().filter { movie ->
        movie.id == movieId
    }
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Gray, elevation = 5.dp) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                        .padding(start = 8.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Button"
                )
                Spacer(modifier = Modifier.width(100.dp))
                Text(text = "Movies Details", modifier = Modifier.fillMaxWidth())
            }

        }
    }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                MovieItem(item = movieList.first())
                Spacer(modifier = Modifier.height(12.dp))
                Divider()
                Text(text = "Movie Image")
                HorizontalScrollImageView(movieList)
            }
        }
    }

}

@Composable
private fun HorizontalScrollImageView(movieList: List<Movie>) {
    LazyRow {
        items(movieList.first().images) { imageUrl ->

            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp), elevation = 8.dp
            ) {
                Image(
                    painter = rememberImagePainter(data = imageUrl),
                    contentDescription = "Movie images"
                )
            }
        }
    }
}