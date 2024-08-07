package com.stopstone.newsapp.data.model

enum class Category(val label: String) {
    DEFAULT("latest"),
    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology"),
}

fun Category.sectionTitle() = label.replaceFirstChar { it.uppercase() }