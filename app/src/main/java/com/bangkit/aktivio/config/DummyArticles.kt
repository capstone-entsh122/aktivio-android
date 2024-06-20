package com.bangkit.aktivio.config

import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.domain.model.ArticleModel

object DummyArticles {
    fun getDummyArticles() : List<ArticleModel> {
        return listOf(
            ArticleModel(
                id = 1,
                title = "The Benefits of Regular Exercise",
                author = "Alice Johnson",
                createdAt = "2024-06-01T08:30:00",
                content = "Regular exercise offers a myriad of benefits, including improved cardiovascular health, stronger muscles, and better mental well-being...",
                thumbnail = R.drawable.img_placeholder
            ),
            ArticleModel(
                id = 2,
                title = "10-Minute Morning Yoga Routine",
                author = "Bob Martinez",
                createdAt = "2024-06-02T07:00:00",
                content = "Start your day with this 10-minute yoga routine designed to energize your body and mind. These simple poses are perfect for beginners...",
                thumbnail = R.drawable.img_placeholder
            ),
            ArticleModel(
                id = 3,
                title = "Healthy Eating: Tips for a Balanced Diet",
                author = "Carol Lee",
                createdAt = "2024-06-03T12:00:00",
                content = "Maintaining a balanced diet is crucial for overall health. Learn how to incorporate a variety of foods into your meals to get the nutrients you need...",
                thumbnail = R.drawable.img_placeholder
            ),
            ArticleModel(
                id = 4,
                title = "Strength Training for Beginners",
                author = "David Kim",
                createdAt = "2024-06-04T10:45:00",
                content = "Strength training is essential for building muscle and increasing endurance. This beginner's guide covers basic exercises and tips to get started...",
                thumbnail = R.drawable.img_placeholder
            ),
            ArticleModel(
                id = 5,
                title = "Mental Health Benefits of Physical Activity",
                author = "Emily Zhang",
                createdAt = "2024-06-05T09:15:00",
                content = "Engaging in regular physical activity can significantly improve your mental health. Discover how exercise can reduce stress, anxiety, and depression...",
                thumbnail = R.drawable.img_placeholder
            )

        )
    }
}