package com.example.chocokcakeV2.domain.auth.entity.user

import com.example.chocokcakeV2.domain.auth.entity.user.type.Role
import com.example.chocokcakeV2.domain.cake.domain.Cake
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@DiscriminatorValue(value = "GENERAL")
@OnDelete(action = OnDeleteAction.CASCADE)
class General(
    id: Long?,
    name: String,
    accountId: String,
    password: String,
    birthDay: LocalDate?,
    role: Role,
    createdAt: LocalDateTime
): User(
    id = id,
    name = name,
    accountId = accountId,
    password = password,
    birthDay = birthDay,
    role = role,
    createdAt = createdAt,
    updatedAt = null
) {
    @OneToMany(mappedBy = "user", cascade = [CascadeType.REMOVE])
    var cakeList: MutableList<Cake> = ArrayList()
        protected set

    fun addCake(cake: Cake){
        this.cakeList.add(cake)
    }
}