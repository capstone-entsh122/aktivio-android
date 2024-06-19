package com.bangkit.aktivio.core.types

enum class SportType {
    FOOTBALL,
    BADMINTON_TENNIS,
    BASKETBALL,
    VOLLEYBALL,
    SWIMMING,
    CYCLING,
    AEROBIC_EXERCISE,
    WALKING_RUNNING,
    GYM,
    YOGA_PILATES,
    DANCE_ZUMBA,
    BILLIARD,
    SOFTBALL_KASTI_CRICKET,
    HIKING,
    GOLF
}

object SportMap {
    val sportMapping = mapOf(
        "Sepakbola/Futsal/Minisoccer" to SportType.FOOTBALL,
        "Badminton/Tennis (olahraga raket)" to SportType.BADMINTON_TENNIS,
        "Basket" to SportType.BASKETBALL,
        "Voli" to SportType.VOLLEYBALL,
        "Renang" to SportType.SWIMMING,
        "Cycling" to SportType.CYCLING,
        "Senam/Aerobik" to SportType.AEROBIC_EXERCISE,
        "Walking/Jogging/Running" to SportType.WALKING_RUNNING,
        "Gym" to SportType.GYM,
        "Pilates/Yoga" to SportType.YOGA_PILATES,
        "Dance/Zumba" to SportType.DANCE_ZUMBA,
        "Billiard" to SportType.BILLIARD,
        "Softball/Kasti/Cricket" to SportType.SOFTBALL_KASTI_CRICKET,
        "Hiking" to SportType.HIKING,
        "Golf" to SportType.GOLF
    )

    fun get(sport: String): SportType {
        return sportMapping[sport] ?: SportType.FOOTBALL
    }
}