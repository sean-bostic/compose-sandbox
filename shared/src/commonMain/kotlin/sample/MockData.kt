package sample
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val avatar: String,
    val role: String,
    val isOnline: Boolean
)

data class Post(
    val id: Int,
    val author: User,
    val title: String,
    val content: String,
    val likes: Int,
    val timestamp: String,
    val imageUrl: String?
)

object MockData {
    val userJohn = User(
        id = 1,
        name = "John Doe",
        email = "john@example.com",
        avatar = "👨‍💻",
        role = "Developer",
        isOnline = true
    )

    val userSarah = User(
        id = 2,
        name = "Sarah Chen",
        email = "sarah@example.com",
        avatar = "👩‍🎨",
        role = "Designer",
        isOnline = false
    )

    val userMike = User(
        id = 3,
        name = "Mike Johnson",
        email = "mike@example.com",
        avatar = "👨‍🔬",
        role = "Data Scientist",
        isOnline = true
    )

    val users = listOf(userJohn, userSarah, userMike)

    val postShort = Post(
        id = 1,
        author = userJohn,
        title = "Hello World",
        content = "This is a short post.",
        likes = 42,
        timestamp = "2 hours ago",
        imageUrl = null
    )

    val postLong = Post(
        id = 2,
        author = userSarah,
        title = "A Long Post About Design",
        content = """
            This is a much longer post with multiple lines of content.
            It demonstrates how UI components handle different content lengths.
            We need to ensure our layouts work well with varying text sizes.
        """.trimIndent(),
        likes = 127,
        timestamp = "1 day ago",
        imageUrl = "🖼️"
    )

    val postNoLikes = Post(
        id = 3,
        author = userMike,
        title = "New Research Findings",
        content = "Just published our latest research paper on machine learning.",
        likes = 0,
        timestamp = "5 minutes ago",
        imageUrl = null
    )

    val posts = listOf(postShort, postLong, postNoLikes)
}