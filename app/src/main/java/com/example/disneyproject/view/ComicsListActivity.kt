package com.example.disneyproject.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.disneyproject.R
import com.example.disneyproject.model.Comic
import com.example.disneyproject.model.Image
import com.example.disneyproject.ui.theme.ProjectTheme
import com.example.disneyproject.viewmodel.ComicsListViewModel

class ComicsListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ComicBonanza()
                }
            }
        }
    }
}

/**
 * Composable to show the main comic details. Needs WAY more formatting and beautifying.
 * Opting in to use Glide Compose experimental API.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ComicComposable(comic: Comic) {
    Column(modifier = Modifier.fillMaxWidth()) {
        GlideImage(
            // Android does not allow cleartext communication over http, replace with https
            model = "${comic.thumbnail?.path?.replace("http","https")}.${comic.thumbnail?.extension}",
            contentDescription = stringResource(R.string.comic_thumbnail_content_desc),
            modifier = Modifier
                .padding(8.dp)
                .height(420.dp)
                .fillMaxWidth()
        )
        //todo formatting and beautification
        Text(
            text = comic.title ?: "Title Unavailable",
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = comic.description ?: "No Description Available",
            modifier = Modifier.padding(8.dp)
        )
    }
}

/**
 * Initially created to handle HTML formatting tags in description, but the text content
 * doesn't seem to be consistent, so need a better way to handle this
**/
@Composable
fun HtmlText(html: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context -> TextView(context) },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)}
    )
}

/**
 * Placeholder for gold plating, intended to create a lazy list of the comics service and allow
 * the user to click on individual comics to bring up a detailed view in the ComicComposable.
 */
@Composable
fun ComicList(comicList: List<Comic>) {
    // lazy column to allow for lazy loading of values - useful for long lists like RecyclerView
    LazyColumn(contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp)) {
        this.items(comicList) { comic ->
            ComicComposable(comic)
        }
    }
}

/**
 * Main composable that gets the data from the ViewModel and then loads the detailed view or
 * creates the loading spinner. observeAsState will recompose composables when the value changes,
 * recreating the detailed view without the loading spinner.
 */
@Composable
fun ComicBonanza(viewModel: ComicsListViewModel = viewModel()) {
    // get the view model and pass the needed data to child composables
    val comicWrapper = viewModel.comicList.observeAsState().value
    if(comicWrapper != null) {
        ComicComposable(comic = comicWrapper.data?.results?.firstOrNull() ?: Comic(title = "Spiderman"))
    } else {
        // Loading Spinner
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjectTheme {
        ComicComposable(Comic(title= "SPOIDAH MAN", description = "THE SPOIDAH MAN",  thumbnail = Image("path", ".jpg")))
    }
}