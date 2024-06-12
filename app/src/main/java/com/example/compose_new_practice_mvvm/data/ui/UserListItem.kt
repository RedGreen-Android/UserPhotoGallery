package com.example.compose_new_practice_mvvm.data.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_new_practice_mvvm.data.model.UserPhotoItem

@Composable
fun UserListItem(userDataItem: UserPhotoItem, onItemClick: (UserPhotoItem) ->Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable { onItemClick(userDataItem) }
    )
    {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val offset = Offset(7.0f, 7.0f)
            Text(
                text = "User: ${userDataItem.user.name}",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    shadow = Shadow(
                        color = Color.DarkGray, offset = offset, blurRadius = 3f
                    )
                ),
                modifier = Modifier.drawBehind {
                    val strokeWidthPx = 1.dp.toPx()
                    val verticalOffset = size.height - 2.sp.toPx()
                    drawLine(
                        color = Color.DarkGray,
                        strokeWidth = strokeWidthPx,
                        start = Offset(0f, verticalOffset),
                        end = Offset(size.width, verticalOffset)
                    )
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Title: ${userDataItem.alt_description}",
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(text = "Bio: ${userDataItem.user.bio}",
                color = Color.DarkGray,
                modifier = Modifier.align(Alignment.End)
            )
            userDataItem.user.location?.let {
                Text(
                    text = it,
                    fontSize = 17.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.align(Alignment.End)
                )
            }
            AsyncImage(
                alpha = 0.7f,
                modifier = Modifier.fillMaxWidth().
                    height(300.dp),
                model = userDataItem.urls.small,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                text = "created: ${userDataItem.created_at}",
                fontSize = 15.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

@Preview(backgroundColor = 0xFFffffff, showBackground = true)
@Composable
fun ArticleItemPreview() {
//    UserListItem(UserPhotoItem(
//        "nice pic",
//        "blue",
//        "Preview title",
//        "The Title",
//        "04/07/21",
//        )
//    )
}