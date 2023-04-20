package com.example.android.daysofwellness

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android.daysofwellness.data.DataSource
import com.example.android.daysofwellness.model.Day
import com.example.android.daysofwellness.ui.theme.DaysOfWellnessTheme


@Composable
fun DaysTopAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(vertical = 8.dp)
            .wrapContentSize(Alignment.Center),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onPrimary
        )

    }
}

@Composable
fun DaysScreen(days: List<Day>, modifier: Modifier = Modifier) {
    Scaffold(topBar = { DaysTopAppBar() }) {
        LazyColumn(modifier = modifier.padding(it)) {
            items(days) { day ->
                DaysMainCard(day = day)
            }
        }
    }
}

@Composable
fun DaysMainCard(day: Day, modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colors.surface),
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Column() {
                    Text(
                        text = stringResource(id = day.dayName),
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = stringResource(id = day.dayMainDescription),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                ExpandedIconButton(expanded = isExpanded, onClick = { isExpanded = !isExpanded })

            }

            if (isExpanded) {
                DayContent(day)
            }
        }
    }
}

@Composable
fun ExpandedIconButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(id = R.string.expand_button_content_description),
            tint = MaterialTheme.colors.onSurface
        )

    }
}

@Composable
fun DayContent(day: Day, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(
                top = 16.dp,
                start = 8.dp,
                bottom = 16.dp,
                end = 8.dp
            )
            .fillMaxWidth()
    ) {

        Card(
            modifier = Modifier.padding(8.dp),
            elevation = 4.dp,
        ) {
            Image(
                painter = painterResource(id = day.dayImageResource),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp)
            )
        }

        Text(
            text = stringResource(id = day.daySubDescription),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 8.dp),
            color = MaterialTheme.colors.onSurface
        )

    }
}



@Composable
@Preview(showBackground = true)
fun DaysTopAppBarPreview() {
    DaysTopAppBar()
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DaysScreenPreview() {
    DaysOfWellnessTheme() {
        DaysScreen(days = DataSource.WellnessDays)

    }
}

@Composable
@Preview(showBackground = true)
fun DaysMainCardPreview() {
    DaysOfWellnessTheme {
        DaysMainCard(
            day = Day(
                R.string.day1, R.string.day_main1, R.string.day_sub1, R.drawable.day1
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DaysSubCardPreview() {
    DaysOfWellnessTheme() {
        DayContent(
            day = Day(R.string.day1, R.string.day_main1, R.string.day_sub1, R.drawable.day1)
        )
    }
}





