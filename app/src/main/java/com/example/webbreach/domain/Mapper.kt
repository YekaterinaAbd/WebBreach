package com.example.webbreach.domain

interface Mapper<N, M> {
    fun from(model: N): M
    fun to(model: M): N
}

