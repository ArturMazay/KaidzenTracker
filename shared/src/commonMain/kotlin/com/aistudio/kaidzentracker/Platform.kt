package com.aistudio.kaidzentracker

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform