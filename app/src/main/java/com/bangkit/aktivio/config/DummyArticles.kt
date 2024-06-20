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
                content = """
        Regular exercise offers a myriad of benefits, including improved cardiovascular health, stronger muscles, and better mental well-being. Engaging in physical activity on a consistent basis can lead to significant improvements in overall health and quality of life.

        One of the most notable benefits of regular exercise is its positive impact on cardiovascular health. By engaging in activities such as running, swimming, or cycling, individuals can strengthen their heart and improve blood circulation. This helps reduce the risk of heart diseases, including heart attacks and strokes. Regular exercise also helps in maintaining healthy blood pressure levels and reduces bad cholesterol, further contributing to heart health.

        In addition to cardiovascular benefits, exercise is crucial for building and maintaining strong muscles and bones. Weight-bearing exercises like weightlifting, resistance training, and even walking can help increase muscle mass and bone density. This is especially important as we age, as it can help prevent conditions such as osteoporosis and sarcopenia. Stronger muscles also improve overall physical performance and reduce the risk of injuries.

        Mental well-being is another significant benefit of regular exercise. Physical activity has been shown to reduce symptoms of depression and anxiety. It promotes the release of endorphins, which are natural mood lifters. Exercise also improves sleep quality, which is vital for mental health. Furthermore, engaging in regular physical activity can enhance cognitive function and reduce the risk of cognitive decline as we age.

        Beyond the physical and mental health benefits, exercise also fosters social interaction and can be a great way to meet new people. Joining a sports team, participating in group fitness classes, or even walking with a friend can provide social support and improve emotional well-being.

        In conclusion, regular exercise is essential for maintaining and improving overall health. It provides numerous benefits, from enhancing cardiovascular health and building strong muscles to improving mental well-being and fostering social connections. Incorporating regular physical activity into your routine is a powerful way to enhance your quality of life and longevity.
    """.trimIndent(),
                thumbnail = R.drawable.img_placeholder
            ),
            ArticleModel(
                id = 2,
                title = "10-Minute Morning Yoga Routine",
                author = "Bob Martinez",
                createdAt = "2024-06-02T07:00:00",
                content = "Start your day with this 10-minute yoga routine designed to energize your body and mind. These simple poses are perfect for beginners...",
                thumbnail = R.drawable.img_placeholder2
            ),
            ArticleModel(
                id = 3,
                title = "Healthy Eating: Tips for a Balanced Diet",
                author = "Carol Lee",
                createdAt = "2024-06-03T12:00:00",
                content = "Maintaining a balanced diet is crucial for overall health. Learn how to incorporate a variety of foods into your meals to get the nutrients you need...",
                thumbnail = R.drawable.img_placholder3
            ),
            ArticleModel(
                id = 4,
                title = "Strength Training for Beginners",
                author = "David Kim",
                createdAt = "2024-06-04T10:45:00",
                content = "Strength training is essential for building muscle and increasing endurance. This beginner's guide covers basic exercises and tips to get started...",
                thumbnail = R.drawable.img_placholder4
            ),
            ArticleModel(
                id = 5,
                title = "Mental Health Benefits of Physical Activity",
                author = "Emily Zhang",
                createdAt = "2024-06-05T09:15:00",
                content = "Engaging in regular physical activity can significantly improve your mental health. Discover how exercise can reduce stress, anxiety, and depression...",
                thumbnail = R.drawable.img_placholder5
            )

        )
    }
}