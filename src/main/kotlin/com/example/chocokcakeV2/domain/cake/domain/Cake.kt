package com.example.chocokcakeV2.domain.cake.domain

import com.example.chocokcakeV2.domain.auth.entity.user.General
import com.example.chocokcakeV2.domain.cake.domain.type.CakeTheme
import com.example.chocokcakeV2.domain.candle.domain.Candle
import com.example.chocokcakeV2.global.common.entity.BaseTimeEntity
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "cake")
class Cake(
    id: Long?,
    cakeTheme: CakeTheme,
    birthDay: LocalDate,
    user: General,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime?
): BaseTimeEntity(
    createdAt = createdAt,
    updatedAt = updatedAt
) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = id
        protected set

    @Column(name = "theme", nullable = false)
    @Enumerated(EnumType.STRING)
    var cakeTheme: CakeTheme = cakeTheme
        protected set

    @Column(name = "birth_day", nullable = false)
    var birthDay: LocalDate = birthDay
        protected set

    @OneToMany(mappedBy = "cake", cascade = [CascadeType.REMOVE])
    var candleList: MutableList<Candle> = ArrayList()
        protected set

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: General = user
}