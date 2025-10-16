package com.compose.sandbox.android

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sample.MockData
import sample.PostCard
import sample.PostList
import sample.UserCard
import sample.UserList


@Preview(name = "User Card - Online", showBackground = true)
@Composable
fun UserCardOnlinePreview() {
    MaterialTheme {
        Surface {
            UserCard(
                user = MockData.userJohn,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(name = "User Card - Offline", showBackground = true)
@Composable
fun UserCardOfflinePreview() {
    MaterialTheme {
        Surface {
            UserCard(
                user = MockData.userSarah,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


@Preview(name = "Post - Short", showBackground = true)
@Composable
fun PostCardShortPreview() {
    MaterialTheme {
        Surface {
            PostCard(
                post = MockData.postShort,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(name = "Post - Long with Image", showBackground = true)
@Composable
fun PostCardLongPreview() {
    MaterialTheme {
        Surface {
            PostCard(
                post = MockData.postLong,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(name = "Post - No Likes", showBackground = true)
@Composable
fun PostCardNoLikesPreview() {
    MaterialTheme {
        Surface {
            PostCard(
                post = MockData.postNoLikes,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


@Preview(name = "User List", showBackground = true)
@Composable
fun UserListPreview() {
    MaterialTheme {
        Surface {
            UserList(
                users = MockData.users,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(name = "Post List", showBackground = true, heightDp = 800)
@Composable
fun PostListPreview() {
    MaterialTheme {
        Surface {
            PostList(
                posts = MockData.posts,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


@Preview(name = "User Card - Dark", showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun UserCardDarkPreview() {
    MaterialTheme {
        Surface {
            UserCard(
                user = MockData.userJohn,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(name = "Post Card - Dark", showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PostCardDarkPreview() {
    MaterialTheme {
        Surface {
            PostCard(
                post = MockData.postLong,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}