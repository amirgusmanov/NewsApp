package kz.amir.newsapp.base.constants

object Categories {
    val regex = "^.*?\\s".toRegex()

    val items = listOf(
        "💼 Business",
        "🎭 Entertainment",
        "🏛️ Politics",
        "🌐 General",
        "🏥 Health",
        "🔬 Science",
        "⚽ Sports",
        "🔧 Technology"
    )
}