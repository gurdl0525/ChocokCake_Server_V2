package com.example.chocokcakeV2.domain.candle.entity

import com.example.chocokcakeV2.domain.cake.entity.Cake
import com.example.chocokcakeV2.domain.candle.entity.type.CandleTheme
import com.example.chocokcakeV2.global.common.entity.BaseTimeEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "candle")
class Candle(
    id: Long?,
    candleTheme: CandleTheme,
    postman: String?,
    letter: String,
    cake: Cake,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime?
):BaseTimeEntity(
    createdAt = createdAt,
    updatedAt = updatedAt
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = id
        protected set

    @Column(name = "theme", nullable = false)
    @Enumerated(EnumType.STRING)
    var candleTheme: CandleTheme = candleTheme
        protected set

    @Column(name = "postman")
    var postman: String? = postman
        protected set

    @Column(name = "letter", nullable = false)
    var letter: String = letter
        protected set

    @ManyToOne
    @JoinColumn(name = "cake_id")
    var cake: Cake = cake
}