package com.example.chocokcakeV2.domain.auth.domain

import com.example.chocokcakeV2.domain.auth.domain.type.Role
import com.example.chocokcakeV2.domain.cake.domain.Cake
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@DiscriminatorValue(value = "GENERAL")
class General(
    id: Long?,
    name: String,
    accountId: String,
    password: String,
    birthDay: LocalDate,
    role: Role,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime?
): User(
    id = id,
    name = name,
    accountId = accountId,
    password = password,
    role = role,
    createdAt = createdAt,
    updatedAt = updatedAt
) {
    @Column(name = "birth_day", nullable = false)
    var birthDay: LocalDate = birthDay
        protected set

    @Column(name = "is_ban", nullable = false)
    var isBan: Boolean = false
        protected set

    @OneToMany(mappedBy = "user", cascade = [CascadeType.REMOVE])
    var cakeList: MutableList<Cake> = ArrayList()
        protected set

    fun addCake(cake: Cake){
        this.cakeList.add(cake)
    }
    fun doBan(ban: Boolean){
        this.isBan = ban
        this.roleList = Role.BANED
    }
}