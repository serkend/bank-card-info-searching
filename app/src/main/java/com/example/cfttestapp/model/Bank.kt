package com.example.cfttestapp.model

data class Bank(
    val city: String,
    val name: String,
    val phone: String,
    val url: String


) {
    override fun toString(): String {
        return "Bank(city='$city', name='$name')"
    }
}