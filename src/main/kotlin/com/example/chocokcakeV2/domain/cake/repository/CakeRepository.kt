package com.example.chocokcakeV2.domain.cake.repository

import com.example.chocokcakeV2.domain.cake.entity.Cake
import org.springframework.data.jpa.repository.JpaRepository

interface CakeRepository: JpaRepository<Cake, Long> {
}