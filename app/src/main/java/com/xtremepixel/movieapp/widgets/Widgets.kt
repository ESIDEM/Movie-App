package com.xtremepixel.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.xtremepixel.movieapp.model.Movie
import com.xtremepixel.movieapp.model.getMovies

@Preview
@Composable
fun MovieItem(item: Movie = getMovies()[0], onItemClick: (String) -> Unit = {}) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(item.id)
            }, shape = RoundedCornerShape(corner = CornerSize(12.dp)), elevation = 8.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp)
            ) {
                Image(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                    contentScale = ContentScale.FillBounds,
                    painter = rememberImagePainter(data = item.images[1], builder = {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }), contentDescription = "Movie Image" )
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = item.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${item.director}", style = MaterialTheme.typography.caption)
                Text(text = "Year: ${item.year}", style = MaterialTheme.typography.caption)
                
                AnimatedVisibility(visible = expanded) {
                    
                    Column {
                       Text(text = buildAnnotatedString {

                           withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                               append("Plot: ")
                           }
                           withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp,
                           fontWeight = FontWeight.Light
                           )){
                                append(item.plot)
                           }
                       })
                        Divider(modifier = Modifier.padding(3.dp))
                        Text(text = "Director: ${item.director}", style = MaterialTheme.typography.caption)
                        Text(text = "Actors: ${item.actors}", style = MaterialTheme.typography.caption)
                        Text(text = "Rating: ${item.rating}", style = MaterialTheme.typography.caption)
                    }
                    
                }
                Icon(imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else
                    Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Keyboard Down", modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = expanded.not()
                        }, tint = Color.DarkGray)

            }
        }


    }
}
